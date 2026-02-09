package ru.job4j.cars.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.repository.CarRepository;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    private static StandardServiceRegistry registry;
    private static SessionFactory sf;

    @BeforeAll
    static void init() {
        registry = new StandardServiceRegistryBuilder()
            .configure().build();
        sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    }

    @AfterAll
    static void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Test
    public void whenCreateCarThenGetTheSameCar() throws Exception {
        var crudRepository = new CrudRepository(sf);
        CarRepository repository = new CarRepository(crudRepository);
        Car car = new Car();
        repository.create(car);
        Car result = repository.findById(car.getId()).get();
        assertThat(car).isEqualTo(result);
    }

    @Test
    public void whenUpdateCarThenGetUpdatedCar() throws Exception {
        var crudRepository = new CrudRepository(sf);
        CarRepository repository = new CarRepository(crudRepository);
        Car car = new Car();
        Engine updated = new Engine();
        updated.setName("BMW");
        repository.create(car);
        car.setEngine(updated);
        repository.update(car);
        Car result = repository.findById(car.getId()).get();
        assertThat(car.getEngine().getName()).isEqualTo(result.getEngine().getName());
    }

    @Test
    public void whenDeleteThenGetNull() throws Exception {
        var crudRepository = new CrudRepository(sf);
        CarRepository repository = new CarRepository(crudRepository);
        Car car = new Car();
        repository.create(car);
        repository.delete(car.getId());
        Optional<Car> result = repository.findById(car.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void whenFindByEngineNameThenGetCar() throws Exception {
        var crudRepository = new CrudRepository(sf);
        CarRepository repository = new CarRepository(crudRepository);
        Engine updated = new Engine();
        updated.setName("BMW");
        Car car = new Car();
        repository.create(car);
        car.setEngine(updated);
        repository.update(car);
        Car result = repository.findByEngineName(updated.getName()).get(0);
        assertThat(car.getEngine().getName()).isEqualTo(result.getEngine().getName());
    }
}