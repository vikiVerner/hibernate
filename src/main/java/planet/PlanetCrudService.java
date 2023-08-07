package planet;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PlanetCrudService {
    public static void main(String[] args) {
        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet planet = new Planet();
        planet.setId("TEST1");
        planet.setName("TestPlanet");
        planetCrudService.createPlanet(planet);
        Planet byIdPlanet = planetCrudService.getById("TEST1");
        System.out.println("byIdPlanet = " + byIdPlanet);
        planetCrudService.changePlanet("TEST1","NewPlanetName");
        planetCrudService.deletePlanetById("TEST1");
    }

    public void createPlanet(Planet planet){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(planet);
        transaction.commit();
        session.close();
    }
    public void deletePlanetById(String id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            session.remove(planet);
            transaction.commit();
        }
    }
    public Planet getById(String id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){

            return session.get(Planet.class, id);
        }
    }

    public void changePlanet(String id, String name){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            planet.setName(name);
            session.persist(planet);
            transaction.commit();
        }
    }
}
