import java.util.* 
 
open class person(var name: String, var birthYear: Int) { 
    var age:Int = Calendar.getInstance().get(Calendar.YEAR) - birthYear 
} 
 
class student(name:String, birthYear: Int, var averageGrade: Float, var extramural: Boolean = false):person(name, birthYear) 
 
class lecturer(name:String, birthYear: Int, var degree: String, var experienceFrom: Int):person(name, birthYear) 
 
//Сортировка людей по возрасту в порядке убывания
fun MutableList<person>.sortByAge():List<person> 
{ 
    return this.sortedByDescending { it.age } 
} 
 
//Сортировка студентов по имени   
fun MutableList<person>.sortByNameStudents():List<student> 
{ 
    return this.filter{ it is student }.sortedByDescending{ it.name } as List<student> 
} 
 
//Сортировка студентов по средней оценке без заочников
fun MutableList<person>.sortByAverageGrade(exceptExtramural : Boolean):List<student> 
{ 
    return if (!exceptExtramural) 
      (this.filter{ it is student } as List<student>).sortedByDescending{ it.averageGrade } 
      else (this.filter{ it is student } as List<student>).filter{ it.extramural }.sortedByDescending{ it.averageGrade } 
} 
  
fun printPerson(info: List<person>): Unit 
{ 
    info.forEach { 
     println("Name: ${it.name}; Age: ${it.age}") 
 } 
}  
 
fun printStudent(info: List<student>): Unit 
{ 
    info.forEach { 
     println("Name: ${it.name}; Age: ${it.age}; averageGrade: ${it.averageGrade}; extramural: ${it.extramural}") 
 } 
} 
   
fun main() { 
    var info = mutableListOf<person>() 
    info.add(student("Potapov Danil", 2000, 4.5f)) 
    info.add(student("Ivanov Pavel", 1999, 3.8f, true)) 
    info.add(student("Dalmatov Oleg", 2001, 4.8f)) 
    info.add(student("Rostislav Aleksandr", 1998, 4.3f)) 
    info.add(student("Ignatiev Yaroslave", 2003, 5.0f)) 
    info.add(student("Lyadov Dmitry", 1993, 5.0f, true)) 
    info.add(lecturer("Kostrov Oleg", 1965, "PhD in Economics", 1999)) 
    info.add(lecturer("Mecheryakov Vasiliy", 1955, "PhD in Mathematics", 1990)) 
    info.add(lecturer("Subotkin Mikhail", 1958, "PhD in Geographics", 1988)) 
    info.add(lecturer("Kirdyashkin Nikolai", 1973, "PhD in Physics", 1995)) 
    info.add(lecturer("Petrov Grigoriy", 1975, "PhD in Mathematics", 1980)) 
     
    println("Исходные данные:") 
    printPerson(info) 
    println("Задание 1.") 
    printPerson(info.sortByAge()) 
    println("Задание 2.") 
    printStudent(info.sortByNameStudents()) 
    println("Задание 3.") 
    printStudent(info.sortByAverageGrade(true)) 
}
