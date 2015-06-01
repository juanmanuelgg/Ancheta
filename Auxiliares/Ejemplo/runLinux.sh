clear
pwd
javac Calculator.java
javac -cp .:junit-4.12.jar CalculatorTest.java
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore CalculatorTest
