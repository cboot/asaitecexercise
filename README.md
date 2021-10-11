# Asaitec fruitshop exercise
## Usage
java -jar fruitshop.jar path/to/products.txt path/to/order.txt

## Notes
The strategy for the Direct discount offer and Free item should have been parametrized, at least within the constructor as I managed to do in the OferXPayY, just didn't have the time to do with all 3 offers.

Since I had no dependency injection and I didn't have the time to build all the scaffolding for a properties file and factory methods the creation of objects has been left on singletons

The UseCases act as typical Domain Driven Design services adapted to Single Responsability Principle of SOLID.


## Project import
Project is built with Maven, and should be imported into IDE as such type.
