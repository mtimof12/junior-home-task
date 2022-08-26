spring.datasource.url=jdbc:h2:~/liquibase;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml

<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
	xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
	xmlns:pro="http://www.liquibase.org/xml/ns/pro"
		http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-latest.xsd
		http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd
		http://www.liquibase.org/xml/ns/pro http://www.liquibase.org/xml/ns/pro/liquibase-pro-latest.xsd">
	<changeSet id="202010211812" author="Julius Krah">
		<createTable tableName="house">
			<column name="id" type="bigint">
				<constraints primaryKey="true" primaryKeyName="house_id_pk" />
			</column>
			<column name="owner" type="varchar(250)">
				<constraints unique="true" uniqueConstraintName="house_owner_unq" />
			</column>
			<column name="fully_paid" type="boolean" defaultValueBoolean="false"></column>
		</createTable>
		<createTable tableName="item">
			<column name="id" type="bigint">
				<constraints primaryKey="true" primaryKeyName="item_id_pk" />
			</column>
			<column name="name" type="varchar(250)" />
			<column name="house_id" type="bigint">
				<constraints nullable="false" notNullConstraintName="item_house_id_nn" />
			</column>
		</createTable>
		<addAutoIncrement tableName="house" columnName="id" columnDataType="bigint" startWith="1" incrementBy="1" />
		<addAutoIncrement tableName="item" columnName="id" columnDataType="bigint" startWith="1" incrementBy="1" />
		<createSequence sequenceName="hibernate_sequence" incrementBy="1" startValue="1" />
		<addForeignKeyConstraint baseTableName="item" baseColumnNames="house_id" constraintName="item_house_id_fk" referencedTableName="house" referencedColumnNames="id" />
	</changeSet>
</databaseChangeLog>