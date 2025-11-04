package com.game.repository;

import com.game.entity.Player;
import com.game.exception.PlayerNotFoundException;
import com.game.hibernatecfg.ConfigSessionFactoryForPlayerDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PreDestroy;

import java.util.List;
import java.util.Optional;

@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {
    private final SessionFactory sessionFactory;
    public PlayerRepositoryDB() {
        this.sessionFactory = ConfigSessionFactoryForPlayerDao.getSessionFactory();
    }
    @Override
    public List<Player> getAll(int pageNumber, int pageSize) {
        try(Session session = sessionFactory.openSession()) {
            Query<Player> queryGetAll = session
                    .createNativeQuery("SELECT * FROM rpg.player LIMIT :pSize OFFSET :multiple", Player.class);
            queryGetAll.setParameter("pSize", pageSize);
            queryGetAll.setParameter("multiple", pageNumber * pageSize);
            return queryGetAll.list();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int getAllCount() {
        int result = 0;
        try(Session session = sessionFactory.openSession()) {
            result = session
                    .createNamedQuery("Player.selectCount", Long.class)
                    .getSingleResult().intValue();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    @Override
    public Optional<Player> save(Player player) {
        Transaction transaction = null;
        Player result = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = (Player) session.save(player);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            throw e;
        } catch (Exception e) {
            transactionRollback(transaction);
            throw e;
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Player> update(Player player) {
        Transaction transaction = null;
        Player result = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = (Player) session.merge(player);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            transactionRollback(transaction);
            throw e;
        } catch (Exception e) {
            transactionRollback(transaction);
            throw e;
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Player> findById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Player player = session.find(Player.class, id);
            return Optional.ofNullable(player);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Optional<Player> delete(long id) throws PlayerNotFoundException {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Player player = findById(id).orElseThrow(PlayerNotFoundException::new);
            session.remove(player);
            transaction.commit();
            return Optional.ofNullable(player);
        } catch (Exception e) {
            transactionRollback(transaction);
            throw e;
        }
    }

    public static void transactionRollback(Transaction transaction) {
        if (transaction != null) transaction.rollback();
    }

    @PreDestroy
    public void beforeStop() {
        ConfigSessionFactoryForPlayerDao.shutdownSessionFactory();
    }
}