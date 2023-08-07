package client;

import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientCrudService {
    public static void main(String[] args) {
        ClientCrudService clientCrudService = new ClientCrudService();
        Client client = new Client();
        client.setName("TestClient");
        clientCrudService.createClient(client);
        Client byId = clientCrudService.getById(11);
        System.out.println("byId = " + byId);
        clientCrudService.changeClient(11,"NewClientName");
        clientCrudService.deleteClientById(11);
    }
    public void createClient(Client client){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                session.persist(client);
            transaction.commit();
        session.close();
    }
    public void deleteClientById(int id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.remove(client);
            transaction.commit();
        }
    }
    public Client getById(int id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){

            return session.get(Client.class, id);
        }
    }
    public void changeClient(int id, String name){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            client.setName(name);
            session.persist(client);
            transaction.commit();
        }
    }
}
