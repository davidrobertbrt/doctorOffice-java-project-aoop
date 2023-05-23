# doctorOffice-java-project-aoop
A project made for the course "Advanced object oriented programming"

## Topic chosen
"Doctor office"(client,medic,appointment)

## Implementation

- Simple class with private/protected attributes and getter / setter methods.

The package `package ro.unibuc.doctorOffice.model` includes all the related models of the project.

- At least 2 different collections capable of managing the previously defined objects (e.g., List, Set, Map, etc.), out of which at least one should be sorted - one-dimensional or two-dimensional arrays will be used in case the collections are not traversed until the checkpoint date.

Within the `PersonReadPanel` class, we have a list that is sorted in ascending order by the name and surname of each person in the clinic. "Person" is a base class for "Doctor" and "Patient".

In each service, we have a corresponding Map for each entity in the application. This facilitates the selection of entities in the menus.

- Using inheritance to create additional classes and their utilization within collections.

In the `PersonReadPanel`, we have a list which uses downcasting and upcasting to print each user in the doctor's office.

```java
List<Person> personList = new ArrayList<>();
personList.addAll(pacientList);
personList.addAll(medicList);
```

The list is then sorted using `Comparator` and then we print the type of each person (Medic/Pacient) and their first name and last name.

```java
personList.sort(Comparator.comparing(Person::getFirstName)
.thenComparing(Person::getLastName));

for (Person person : personList) {
String type = (person instanceof Pacient) ? "Pacient" : "Medic";
System.out.println(type + ": " + person.getFirstName() + " " + person.getLastName());
}
```

- At least one service class that exposes the operations of the system.

We have services classes which use the CRUD operations from repositories and also handle the Maps of each entity from the application.

They can be found in the `ro.unibuc.doctorOffice.service.*` package

- o clasa Main din care sunt făcute apeluri către servicii

We call the services in the main class, first at load and then the `ro.unibuc.doctorOffice.utils.*` handle the operations to the services through a CLI implementation.

-- The application must ensure data persistence, either through file storage or a database.

- File-based services can be replaced with database persistence services using JDBC.
- Services will be implemented to expose create, read, update, and delete (CRUD) operations for at least 4 of the defined classes.

The `ro.unibuc.doctorOffice.repository.*` package contains the CRUD operations on each entity using JDBC.

Connection to the database is made using the `DatabaseConfig` class located in `ro.unibuc.doctorOffice.config`

- Upon program startup, the data will be loaded from files using the created services.

```java
public static void loadAll()
{
    pacientService.loadPacients();
    medicService.loadMedics();
    prescriptionService.load();
    reportService.load();
    appointmentService.load();
}
```

- A service will be implemented to write to a CSV file or a database every time one of the actions described in the first stage is executed. The structure of the file/table will be: action_name, timestamp.

This service is located in the `audit` package.

The `send` function of this class, records an action and its timestamp in the database.

```java

public static int send(String nameAction,Date dateAction)
{
    String sqlQuery = "INSERT INTO audit(name_action,datetime_action) VALUES(?,?)";

    try(PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sqlQuery))
    {
        Date current = new Date();
        Timestamp timestamp = new Timestamp(current.getTime());
        statement.setString(1,nameAction);
        statement.setTimestamp(2, timestamp);

        return statement.executeUpdate();
    }
    catch(SQLException ex)
    {
        System.out.println(ex.getMessage());
        return -1;
    }
}

```
