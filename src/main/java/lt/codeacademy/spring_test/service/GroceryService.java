package lt.codeacademy.spring_test.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.spring_test.entity.Grocery;
import lt.codeacademy.spring_test.repository.GroceryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GroceryService {

    private final GroceryRepository groceryRepository;

    public void addGrocery(Grocery grocery) {
        groceryRepository.saveAndFlush(grocery);
    }

    public void addTestGrocery() {
        Grocery apple = new Grocery();
        apple.setName("Apple");
        apple.setCategory("Fruit");
        apple.setPrice(0.15);
        addGrocery(apple);

        Grocery banana = new Grocery();
        banana.setName("Banana");
        banana.setCategory("Fruit");
        banana.setPrice(0.20);
        addGrocery(banana);

        Grocery bread = new Grocery();
        bread.setName("Bread");
        bread.setCategory("Bakery");
        bread.setPrice(1.50);
        addGrocery(bread);

        Grocery milk = new Grocery();
        milk.setName("Milk");
        milk.setCategory("Dairy");
        milk.setPrice(1.20);
        addGrocery(milk);

        Grocery cheese = new Grocery();
        cheese.setName("Cheese");
        cheese.setCategory("Dairy");
        cheese.setPrice(2.50);
        addGrocery(cheese);

        Grocery chicken = new Grocery();
        chicken.setName("Chicken Breast");
        chicken.setCategory("Meat");
        chicken.setPrice(5.00);
        addGrocery(chicken);

        Grocery rice = new Grocery();
        rice.setName("Rice");
        rice.setCategory("Grains");
        rice.setPrice(2.00);
        addGrocery(rice);

        Grocery potato = new Grocery();
        potato.setName("Potato");
        potato.setCategory("Vegetable");
        potato.setPrice(0.30);
        addGrocery(potato);

        Grocery carrot = new Grocery();
        carrot.setName("Carrot");
        carrot.setCategory("Vegetable");
        carrot.setPrice(0.25);
        addGrocery(carrot);

        Grocery eggs = new Grocery();
        eggs.setName("Eggs");
        eggs.setCategory("Dairy");
        eggs.setPrice(2.75);
        addGrocery(eggs);

        System.out.println("All grocery has been saved");
    }

    public Page<Grocery> findAllPageable(Pageable pageable) {
        return groceryRepository.findAll(pageable);
    }
    public int returnRepSize(){
        return groceryRepository.findAll().size();
    }

}
