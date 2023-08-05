package com.rickyslash.simplecleanarchitecture.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.rickyslash.simplecleanarchitecture.R
import com.rickyslash.simplecleanarchitecture.databinding.ActivityMainBinding
import com.rickyslash.simplecleanarchitecture.presentation.MainViewModel
import com.rickyslash.simplecleanarchitecture.presentation.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.setName("Rickyslash")
        viewModel.message.observe(this) {
            binding.tvWelcome.text = it.welcomeMessage
        }
    }
}

// aspect that is improved by using `Design Pattern`:
// - Reusability
// - Extensibility
// - Scalability
// - Maintainability

// 3 Pattern in Software Development:
// - Creational Pattern
// - Structural Pattern
// - Behavioral Pattern

// Creational Pattern: to make an instance from object without knowing how it's being made in step-by-step. it put forwards flexibility. example of creational pattern:
// - Singleton Pattern: to make sure an object only has 1 instance
// --- Singleton mechanism using "object":
// object CarFactory {
//val cars = mutableListOf<Car>()
//fun makeCar(horsepowers: Int): Car {
//    val car = Car(horsepowers)
//    cars.add(car)
//    return car
//}
//}
// --- Singleton mechanism using "companion object":
// companion object {
//@Volatile // this so instance value not being added to cache (so the value always up-to-date)
//private var INSTANCE: TourismDatabase? = null
//
//fun getInstance(context: Context): TourismDatabase =
//    INSTANCE ?: synchronized(this) { // `synchronized` so only 1 thread can access this at a time
//        val instance = Room.databaseBuilder(
//            context.applicationContext,
//            TourismDatabase::class.java,
//            "Tourism.db"
//        ).build()
//        INSTANCE = instance
//        instance
//    }
//}
// --- example of Singleton: `Firebase.getInstance()`
// Builder Pattern: To make object gradually but only focus on the shape of the object (only define a couple of variable of the object, not all)
// --- example of Builder Pattern application:
//     class Handphone private constructor(builder: Builder) {
//private val processor: String = builder.processor
//private val battery: String = builder.battery
//private val screenSize: String = builder.screenSize

// Builder class
//class Builder(processor: String) {
//    var processor: String = processor // wajib ada
//
//    var battery: String = "4000MAH"
//    var screenSize: String = "6inch"
//
//    fun setBattery(battery: String): Builder {
//        this.battery = battery
//        return this
//    }
//
//    fun setScreenSize(screenSize: String): Builder {
//        this.screenSize = screenSize
//        return this
//    }
//
//    fun create(): Handphone{
//        return Handphone(this)
//    }
//}
//}
// --- example of calling Builder Pattern:
//  val myPhone = Handphone.Builder("Octa-core") // mandatory
//.setBattery("5000MAH")                     // optional
//.create()
// --- example of Builder Pattern Usage:
//     AlertDialog.Builder(this)
//.setTitle("Judul dialog")
//.setMessage("Pesan dalam dialog")
//.show()
// - Factory Method Pattern: make interface and inner class for any specific implementation
// --- example of Method Pattern application:
//  interface Handphone {
//var processor: String
//var battery: String
//var screenSize: String
//}
//
//class HandphoneNexus5 : Handphone {
//    override var processor = "Snapdragon"
//    override var battery = "2300 mAh"
//    override var screenSize = "4.95 inch"
//}
//
//class HandphoneNexus9 : Handphone {
//    override var processor = "Nvidia Tegra"
//    override var battery = "6700 mAh"
//    override var screenSize = "8.9 inch"
//}
//
//enum class Type {
//    NEXUS5, NEXUS9
//}
//
//class HandPhoneFactory {
//    companion object {
//        fun createHandphone(type: Type): Handphone {
//            return when (type) {
//                Type.NEXUS5 -> HandphoneNexus5()
//                Type.NEXUS9 -> HandphoneNexus9()
//            }
//        }
//    }
//}

//fun main() {
//    val myPhone = HandPhoneFactory.createHandphone(Type.NEXUS5)
//}
// --- example Method Pattern Usage:
//     class HomeViewModelFactory (private val tourismRepository: TourismRepository) : ViewModelProvider.Factory {

//override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//        return HomeViewModel(tourismRepository) as T
//    }
//    throw IllegalArgumentException("Unknown ViewModel class")
//}
//}
// - Dependency Injection Pattern: injecting another class that is a dependency of the current class as parameter
// --- dependency is an object that is needed to run a process
// --- without dependency injection, this problem will arose:
// ----- both class (ex: car & engine) will be tightly coupled (if want to make new object with different engine, need to make a whole car class)
// ----- unit testing will be hard, as it need a real object (cannot do test-double (fake object/mock object))
// --- example Dependency Injection application:
//     class Car(private val engine: Engine) {
//fun start() {
//    engine.start()
//}
//}
//
//fun main() {
//    val engine = Engine()
//    val car = Car(engine)
//    car.start()
//}
// Structural Pattern: focus on how to structure the code. example of structural pattern:
// - Facade Pattern: hide details by making new function. the example is making repository
// --- example Facade Pattern application:
// interface CommentService {
// @POST("v1/comment")
// fun createComment(@Body request: Comment): Call<EmptyResult>
// @GET("v1/comment")
// fun getComment(@Body request: Comment): Call<MutableList<Comment>>
// }
// usage
// val retrofit = Retrofit.Builder()
//    .baseUrl("https://www.yourappurl.com") // this "request" is from repository
//    .addConverterFactory(GsonConverterFactory.create()) // this "parsing" is from repository
//    .build()
// val api = retrofit.create<CommentService>(CommentService::class.java)
// - Adapter Pattern: act as a connector to connect 2 interface that isn't suitable to work together by making a class to convert one of the class so they could work
// --- example Adapter Pattern usage:
//     class TourismAdapter(private val listData: ArrayList<TourismEntity>) : RecyclerView.Adapter<TourismAdapter.ListViewHolder>() {

//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//    val inflater = LayoutInflater.from(parent.context)
//    val itemView = inflater.inflate(R.layout.item_list_tourism, parent, false)
//    return ListViewHolder(itemView)
//}
//
//override fun getItemCount() = listData.size
//
//override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//    holder.bind(listData[position])
//}
//...
//}
// Behavioral Pattern: focus on how to manage communication between class, so the responsibility between each class could be known easily. Behavioral Pattern example:
// - Observer Pattern: to get latest update & event from an object by subscribing to it first. example usage is the usage of Broadcast Receiver, by getting an event that is published by OS and LiveData to get the latest data
// - Method Pattern: if have 2 component with similar component, one could make a template by making abstract class as superclass, with default implementation
// - example Method Pattern usage:
// ----------------------------------------
// Superclass
// ----------------------------------------
//abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
//
//    protected abstract fun loadFromDB(): LiveData<ResultType>
//
//    protected abstract fun shouldFetch(data: ResultType?): Boolean
//
//    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
//
//    protected abstract fun saveCallResult(data: RequestType)
//
//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//
//        //default implementation
//    }
//
//    fun asLiveData(): LiveData<Resource<ResultType>> = result
//}
// ----------------------------------------
// Subclass
// ----------------------------------------
//fun getAllTourism(): LiveData<Resource<List<TourismEntity>>> =
//    object : NetworkBoundResource<List<TourismEntity>, List<TourismResponse>>(appExecutors) {
//        override fun loadFromDB(): LiveData<List<TourismEntity>> {
//            return localDataSource.getAllTourism()
//        }
//
//        override fun shouldFetch(data: List<TourismEntity>?): Boolean =
//            data == null || data.isEmpty()
//
//        override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> =
//            remoteDataSource.getAllTourism()
//
//        override fun saveCallResult(data: List<TourismResponse>) {
//            val tourismList = DataMapper.mapResponsesToEntities(data)
//            localDataSource.insertTourism(tourismList)
//        }
//    }.asLiveData()


// ARCHITECTURAL PATTERN & CLEAN ARCHITECTURE //


// Architectural Pattern is a higher level of Design Pattern
// MVx Pattern (MVVM, MVP, MVC, dll): separating code into 3 layer
// - Model: to manipulate data
// - View: to get input & display data
// - x: mediator between View & business process
// infographic: https://www.dicoding.com/academies/165/tutorials/10284#

// Clean Architecture is a higher abstraction compared to MVx Pattern
// - it sepearate app to layer: UI, Presenter, Domain (use Case), & Entities
// - infographic: https://d17ivq9b7rppb3.cloudfront.net/original/academy/202009080924302955396120023867f9656939652075cc.png
// according to the infographic:
// - outside layer dependent to inner layer,
// - inner layer is okay not to know the outer layer

// Examples of architecture in Software Engineering: Hexagonal Architecture, Onion Architecture, Lean Architecture, BCE, Clean Architecture, etc

// Reason to follow clean architecture:
// - Independent of Framework: not dependent to framework implementation. framework only act as a tool
// - Testable: business code could be tested without UI, Database, or another external element
// - Independent of UI: UI could be easily changed without changing the whole system
// - Independent of Database: not dependent on certain database framework. could be changed easily
// - Independent of External: business process no need to know what's outside

// Clean Architecture layer with it's business needs:
// infographic: https://d17ivq9b7rppb3.cloudfront.net/original/academy/20200908092517a73f008f9983e46d26a9d47cd143cd12.png

// 4 common level on Clean Architecture:
// - Entities: to save business entity data that has no method. it is so plain that one could use it to another app (KMM, iOS, web, etc). Entities should not be changed
// - Use Case (Interactor): to manipulate business data and achieve business objective. should not be changed when there is an external change (UI/DB)
// - Interface Adapter: mediator between UI & business (use case) layer. it control the flow of request to the interface. it is ViewModel in MVVM, presenter in MVP, Repository if it's to the data layer
// - Frameworks: the most outside part. it connect with OS Framework (Android), like UI, database, or network (datasource)

// project is commonly separated to: Presentation, Domain, & Data
// infographic:  https://d17ivq9b7rppb3.cloudfront.net/original/academy/20200908092548bcc70d4613c9613ceecbdffd9138774d.png

// Presentation layer: contains UI & Presenter/ViewModel to control UI
// Domain Layer: contains Entities, Use Case, & Repository interface. Core layer that is really connected to business process
// Data Layer: contains Repository implementations & Datasource

// Dependency Rule: Presentation Layer & Data need to be dependent to the domain (Use Case & Entities)
// to make Data Layer not dependent with Domain Layer, use Dependency Inversion
// make sure Domain Layer (inner) (Use Case & Entities) not accessing class in Presentation Layer or Data Layer (outer)