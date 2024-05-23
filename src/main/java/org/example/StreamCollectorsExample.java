package org.example;

import java.util.*;
import java.util.stream.Collectors;

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        //Создайте список заказов с разными продуктами и их стоимостями.
        List<Order> result1 =orders.stream().toList();
      //  result1.forEach(order->System.out.println(order.getProduct()+" "+order.getCost()));
        System.out.println("------------------------------");

        //Группируйте заказы по продуктам.
        Map<String,List<Order>> result2=orders.stream().collect(Collectors.groupingBy(Order::getProduct));
     //   System.out.println(result2);
        System.out.println("------------------------------");

        //Для каждого продукта найдите общую стоимость всех заказов.
        Map<String, Double> result3 = orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));
      //  System.out.println(result3);
        System.out.println("------------------------------");

        //Отсортируйте продукты по убыванию общей стоимости.
        Map<String,Double> result4 = result1.stream().collect(Collectors.groupingBy(
                Order::getProduct,
                LinkedHashMap::new, Collectors.summingDouble(Order::getCost)));
   //     System.out.println(result4);
        System.out.println("------------------------------");

        //Выберите три самых дорогих продукта.
        List<Order> result5 =orders.stream().sorted(Comparator.comparingDouble(Order::getCost).reversed()).limit(3).toList();
     //   result5.forEach(order->System.out.println(order.getProduct()+" "+order.getCost()));
        System.out.println("------------------------------");

        //Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
        double result6 =result5.stream().mapToDouble(i -> i.getCost()).sum();
        result5.forEach(order->System.out.println(order.getProduct()+" "+order.getCost()));
        System.out.println(result6);
        System.out.println("------------------------------");
    }
}