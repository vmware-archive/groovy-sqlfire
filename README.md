# groovy-sqlfire

A support library to add support for writing custom code in SQLFire using Groovy.

## Build
The groovy-sqlfire project uses maven for builds.  Install the appropriate JDK to your machine, and then install maven.  Then, issue a mvn package to create the distribution.

## Install
Copy the zip archive to the `vFabric_SQLFire_11_b[nnnnn]` directory of every machine in the data grid, and extract the contents to the ext-lib directory.

Next, create the schema and table to house the Groovy scripts that will be replicated to all servers in the grid.  Issue the following commands:

    drop table if exists GROOVY.SCRIPTS;
    
    drop schema if exists GROOVY restrict;
    
    create schema GROOVY;
    
    create table GROOVY.SCRIPTS(name varchar(1024), script long varchar, PRIMARY KEY(name));    

## Use
To use any of the following features, you first need to load the scripts you want to invoke into SQLFire.  To do this, simply insert the scripts into the GROOVY.SCRIPTS table like the following:

    insert into GROOVY.SCRIPTS VALUES('test.groovy', 'def getRow(schema, table, key) { println "Schema: ${schema}, Table: ${table}, Key: ${key}"}');    

This statement creates a script with a single method called `getRow` that accepts three parameters.  The method itself simply outputs the three parameters to the server log.

### RowLoader
To create a RowLoader using Groovy, create a Groovy script that has a method defined in it called getRow that accepts  parameters for the schema and table name the loader is attached to, and then a parameter to accept the value (or values) for the primary key of the table.  Insert that script into the GROOVY.SCRIPTS table similarly to the INSERT statement in the "Use" section.

After loading the script into the DB, attach the script to the table you want to load rows into with a script similar to the following:

    call sys.attach_loader('app', 'customer', 'com.gopivotal.sqlfire.extensions.groovy.GroovyRowLoader', 'test.groovy');

For this `attach_loader` call, we're passing in the schema, and table name of `app.customer`, and then specifying the `GroovyRowLoader` support class to use to run the script we inserted into the `GROOVY.SCRIPTS` table called `test.groovy`.

The script's `getRow` method will be called and then the script should return an object that implements java.util.List, or java.sql.ResultSet.  List is likely preferential, since lists are quite easy to work with in Groovy scripts.

For more information, refer to the [RowLoader API reference](http://www.vmware.com/support/developer/vfabric-sqlfire/111-api/com/vmware/sqlfire/callbacks/RowLoader.html), and the [section on RowLoaders in the SQLFire reference](http://pubs.vmware.com/vfabric53/topic/com.vmware.vfabric.sqlfire.1.1/developers_guide/topics/cache/rowloader.html).

### Writers and Listeners
Writers and Listeners both implement the [EventCallback](http://www.vmware.com/support/developer/vfabric-sqlfire/111-api/com/vmware/sqlfire/callbacks/EventCallback.html) interface in SQLFire.  Writers allow you to intercept events in SQLFire before they occur, and can veto them by throwing an SQLException.  Listeners allow you to react to events after they occur.

For more info, refer to the [Writer and Listener section of the SQLFire reference](http://pubs.vmware.com/vfabric53/topic/com.vmware.vfabric.sqlfire.1.1/caching_database/writers-listeners-about.html).

### Asynchronous Event Listeners
Asychronous Event Listeners implement the [AsyncEventListner]() interface in SQLFire.

For more info, refer to the [Handling DML Events Asynchronously section of the SQLFire reference](http://pubs.vmware.com/vfabric53/topic/com.vmware.vfabric.sqlfire.1.1/caching_database/asyncevent-chapter.html).

### Functions
TODO

### Procedures
TODO