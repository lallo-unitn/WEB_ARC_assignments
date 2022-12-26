package it.unitn.disi.web.rg209272.demoh2_2;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class Program {
    public static void main(String[] a) { new Program();}
    Program() {
        Employee a = new Employee("Pippo", "DePippis");
        Employee b = new Employee("Paolino", "Paperino");
        System.out.println(a);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(a);
            session.save(b);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach(s -> System.out.println(s.getLastName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}