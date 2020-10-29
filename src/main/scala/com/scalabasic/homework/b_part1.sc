
//Exercise 1 (Mandatory)
//Submit three in-class exercises:
//● The first two in a .sc format

//Breakout Room1
def greet1(name: String): String = s"Hello $name, How are you doing?"
def greet2(name: String): String = s"Hello $name, How was your day?"

def frame(name:String, f:String => String): String ={
  f(name)
}
println(frame("Alice", greet1))
println(frame("Nick", greet2))

//Breakout Room2
//Create a List of integer from 1-9
val intList = List(1,2,3,4,5,6,7,8,9)

//Remove the even numbers and make a new list
val oddList = intList.filter((num:Int)=> (num%2!=0))
println(oddList)

//write a cube function x=>x^3
def cube(x:Int):Int = x * x * x

//Map the cube function to the filtered list and print the result in a separate line
val result = oddList.map((x:Int)=>cube(x:Int))
println(result)

//Exercise 2 (Mandatory)
//Generate a list from 1 to 45 then apply .filter to compute the following results:
val x:List[Int] = (1 to 45).toList

//● Sum of the numbers divisible by 4
val listOfNumDivisibleByFour = x.filter((num:Int)=>(num%4==0))
val sumOfNumDivisibleByFour = listOfNumDivisibleByFour.reduce((x:Int, y:Int)=> (x+y))
println(sumOfNumDivisibleByFour)

//● Sum of the squares of the numbers divisible by 3 and less than 20

val ListOfNumDivisibleByThreeAndLessThanTwenty = x.filter((num:Int)=>(num%3==0 && num<20) )
val sumOfNumDivisibleByThreeAndLessThanTwenty = ListOfNumDivisibleByThreeAndLessThanTwenty.reduce((x:Int, y:Int)=> (x+y))
println(sumOfNumDivisibleByThreeAndLessThanTwenty)

//Exercise 3 (Mandatory)
//Write a max function that picks the max of two numbers and another get_max function
//to call the first one with inputs.

def max(x:Int, y:Int):Int = {
  if (x>y) x
  else y
}

def get_max(x:Int, y:Int, f:(Int,Int)=>Int):Int = {
  f(x,y)
}
println(get_max(3,5,max))





