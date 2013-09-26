drop table if exists GROOVY.SCRIPTS;

drop schema if exists GROOVY restrict;

create schema GROOVY;

create table GROOVY.SCRIPTS(name varchar(1024), script long varchar, PRIMARY KEY(name));

--insert into GROOVY.SCRIPTS VALUES('test.groovy', 'def getRow(schema, table, key) { println "Schema: ${schema}, Table: ${table}, Key: ${key}"}');

--call sys.attach_loader('app', 'customer', 'com.gopivotal.sqlfire.extensions.groovy.GroovyRowLoader', 'test.groovy');