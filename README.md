# Hello Compose Movies

### App de exemplo utilizando Jetpack Compose, Clean Architecture e padrão MVI

### Arquitetura
Foi utilizada a arquitetura Clean com abstrações de camadas arquiteturais e por features
### Camadas
- **Data**:        
  /local/Dao      
  /local/Entities      
  /remote/model
- **Domain**
- **Navigation**
- **Services**
-  **Presentation**
- **Ui**
- **Features**:     
  features/login  
  features/home  
  features/details

### Padrão MVI
O padrão MVI é um ótimo padrão para se trabalhar com o Compose , nele podemos citar as seguintes vantagens:
- Fluxo de dados unidirecional e cíclico — é um padrão de design em que o estado flui para baixo e os eventos fluem para cima. Com essa abordagem, podemos separar elementos que podem ser compostos que exibem um estado na IU das partes do seu aplicativo que armazenam e alteram o estado.
- Fonte única de verdade — a entidade principal nesta arquitetura, da qual emanam todos os outros benefícios.
- Facilidade de depuração. Como temos apenas uma fonte de dados, é fácil depurá-la.
- Facilidade de teste. Na maioria dos casos, basta verificar um novo estado após algum evento para validar o código, e isso é bastante fácil de fazer com a implementação correta.

![Foto MVI](img/mvi.png)

Utilizamos uma  **data class** para representar os possíveis estados da UI <br>

Ex: **LoginUiStates**

![Foto uiStates](img/uiStates.png)

Utilizamos uma **sealed class** para representar os eventos da tela        
<br>        
Ex: **LoginEvents**

![Foto events](img/events.png)

Como funciona: <br>        
Basicamente a **ViewModel** recebe um Evento da View,        
este **Evento** dispara uma acão, e o efeito desta ação causa uma alteracão na classe de estados da View **UiStates**, que  faz com que o recomposition do Compose renderize as alterações na View para corresponder ao novo estado  da View

### UI
Para criação das Views foi utilizado o Jetpack Compose, uma api para criação declarativa de UIs nativas,  recomendado pelo Android.

### Gerenciamento de dependências

O gerenciamento de dependências do projeto foi feito com **Gradle Plugin e Kotlin DSL**

Algumas vantagens de se usar kotlin DSL e não Groovy:
- Preenchimento automático do código(_autocomplete_)
- Sintaxe Kotlin destacada (_highlighting_)
- Navegação (tracking) de código para as fontes
- Documentação
- Refatorações etc …
- Scalabilidade: existem muitas configurações que podem ser compartilhadas entre módulos, bibliotecas, testes etc, e o que envolve um tempo considerável gerenciando várias dependências e versões nos vários módulos do projeto.        
  ![Foto gradle](img/buildSrc.png)


Utilizamos a seguinte estrutura:  <br>        
**Deps** : onde guardamos as referências das dependências/libs do projeto        
![foto deps](img/deps.png)

**Versions**: onde guardamos as versões dessas dependências/libs

![foto version](img/version.png)


**DependenciesXtensions** onde agrupamos as dependências por afinidades

![foto dependencias](img/depxt.png)


para declarar uma dependência utilizamos a extensão,        
invocando no ***build.gradle.kts*** do módulo que desejarmos colocar a dependência :

Ex:    
***coilDependencies()***

### Injeção de dependências
Foi utilizada a lib koin para injeção de dependências do projeto

### Navegação
A navegação do app utiliza a API do Compose Navigations e para tal temos uma **sealed class** que guarda as nossas rotas de navegação:  <br>        
Routes.kt        
![Foto Rotas](img/routes.png)


### Features do App
- LoginScreen.kt <br>
- HomeScreen.kt <br>
- DetailScreen.kt <br>

### Prints do app

![Tela Splash](img/splash.png)   ![Tela Home](img/home.png)    ![Tela Detalhes](img/details.png)   ![Tela Login](img/login.png)


### Instruções específicas do projeto
Para realizar o Login utilize a        
Senha fake : abc123

Foi utilizada a lib **AnimatedNavHost** para navegação entre telas, com efeitos de transições entre as telas, porém esta lib ainda não conta com suporte a testes, e por tal motivo foi criado um arquivo com a lib padrão do compose apenas para rodar os testes de navegação, para isso execute os procedimentos citados abaixo:        
Para rodar teste navigation:

Substitua o componente [AnimatedNavHost] no arquivo [NavvHost] pelo [NavHost] padrao do compose, exemplo no arquivo [NavHostForTest] Depois altere na [MainActivity] de [val navController = rememberAnimatedNavController()] para [val navController = rememberNavController()] Feito isso rode o teste

Também pode se utilizar a branch "teste" caso não queira realizar nenhuma das alterações acima mencionadas, e rodar os testes nela

### Testes unitários (tests)

##### Presentation
- /presentation/detail/DetailViewModelTest.kt
- /presentation/home/HomeViewModelTest.kt
- /presentation/login/LoginViewModelTest.kt
##### Utils

- /DateConversionTest
##### Api

- /remote/service/PopularMoviesServiceTest

Para rodar os testes digite no terminal:

***./gradlew testDebugUnitTest***

![Foto tests](img/gradleUnit.png)


### Testes instrumentais (androidTests)
##### Room Data Base

- /local/RoomDbTest.kt
##### Navigation

- /navigation/NavigationTest
##### Ui

- /ui/DetailUiTest
- /ui/HomeUiTest
- /ui/LoginUiTest

![Foto Testes Instrumentais](img/androidTest.png)

![Video Testes Instrumentais](/img/espresso.webm)


Para rodar os testes digite no terminal:    
***./gradlew connectedDebugAndroidTest***  
![Foto tests](img/gradleIns.png)


### Cobertura de testes - Jacoco
![Tela Jacoco](jacoco.png)

Para rodar o jacoco utilize os scripts da pasta/scripts/jacoco no gradle:        
![Tela Jacoco](img/jacocos.png)

ou pelo terminal:      
antes set a variavel cache para false,  no arquivo gradle.properties    
*org.gradle.unsafe.configuration-cache=**false*** rode o comando ->  ***./gradlew jacocoCoverage*** rode o comando ->  ***./gradlew jacocoReport***

Para abrir no Browser:    
PATH --> /app/build/reports/coverage/androidTest/debug/connected/index.html

### Sumário de libs utilizadas no projeto

#### JetpackCompose
androidx.activity:activity-compose:${activityComposeVersion}        
androidx.navigation:navigation-compose:${composeNavVersion}

#### Coil
io.coil-kt:coil-compose:${coilComposeVersion}

#### Koin
io.insert-koin:koin-androidx-compose:${koinComposeVersion}

#### Interceptor
com.squareup.okhttp3:logging-interceptor:${interceptorVersion}

#### Jacoco
org.jacoco:org.jacoco.core:${jacocoVersion}

#### Material Design 3
androidx.compose.material3:material3

#### Retrofit
com.squareup.retrofit2:retrofit:${retrofitVersion}      
com.squareup.retrofit2:converter-gson:${retrofitVersion}

#### Room
androidx.room:room-runtime:${roomVersion}      
androidx.room:room-compiler:${roomVersion}

#### Splash Screen
androidx.core:core-splashscreen:${splashScreenVersion}

#### Turbine
turbineFlowTests =  app.cash.turbine:turbine:${turbineVersion}