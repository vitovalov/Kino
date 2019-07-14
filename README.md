
## Architecture
This implementation is based on [MVP + Clean architecture](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/) and S.O.L.I.D principles.

Inner layers know nothing about outer layers. This facilitates decoupling and testing.

**Other software design patterns used in this project:** 

- Repository pattern 
 

#### Packages definitions:
- **data**: It contains all the data accessing and manipulating components.
- **domain**: It contains all the business logic split in use cases.
- **ui**: View classes along with their corresponding Presenters.
- **extensions**: Kotlin extensions.
- **mapper**: Contains classes that facilitate model object mapping from one layer to another.
- **di**: Dependency providing classes using Koin.
- **model**: 
    - Bo (business object), 
    - Dto (data transfer object), 
    - Uo (ui object), 
    - Lo (local 'database' object).


## Dependency Inversion - Injection
**Koin** has been chosen due to its simplicity and clarity.
The only drawback is that the error checks are performed at runtime. 

Despite **Dagger** being maintained by Google, it involves a lot of boilerplate setup.

## Thread handling
Coroutines are being used in this implementation since as advised by the android community and throughout several Google IO'19 talks.

## Networking
**Retrofit 2.6.0** is the most popular and trusted networking library.

This version also enables to use it with **Coroutines**.

## Database
Instead of Room database, **Realm** has been chosen due to its minimal configuration and great reliability.

### Other considerations
- Image loading: **Glide** is one of the best async image loading libs out there. As benchmarks show, it's the fastest one when it comes to image downloading and loading from cache.   
- Timber for logging: chosen for this project to save time while implementing the core functionality. It can be enhanced and fits well when the project needs to scale.

# To improve

#### Code quality  
- Integrate **crashlytics** or similar to have better insight of user runtime exceptions.
- Integrate continuous inspection of the code quality using **SonarQube** to perform automatic reviews static analysis of code to detect bugs, code smells, and security vulnerabilities.
- Integrate checks with a Kotlin linter with built-in formatter **Ktlint**.
- Add Cache validation strategy.

##### Screen rotation
Use **ViewModel** from [*Architecture Components*](https://developer.android.com/topic/libraries/architecture/index.html) to maintain state across device configuration changes. 

##### Popular shows ordering
Current implementation loads tv show list from `/tv/popular` endpoint in pages.

Each page is ordered by tv show average rating.

This could be improved ordering every new page of items relative to all previous items. This would require to use **DiffUtils** and calling `notifyItemRangeChanged` instead of simply `notifyItemRangeInserted`

## Demo
<img src="art/demo_online.gif" width="300" height="500" />

<img src="art/demo_offline.gif" width="300" height="500" />
