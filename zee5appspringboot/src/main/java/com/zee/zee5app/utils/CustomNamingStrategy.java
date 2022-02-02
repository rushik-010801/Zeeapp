package com.zee.zee5app.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {
	private final static String POSTFIX = "_table";
	//by default all table names should be ended by _table
	
	@Override
	public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		if(identifier == null)
			return null;
		final String newName = identifier.getText() + POSTFIX;
		// helps in getting Table name if @table annotation is used, if not entity name, if not Class name.
		
		return identifier.toIdentifier(newName);
	}
	@Override
	public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment context) {
		// TODO Auto-generated method stub
		if(identifier == null)
			return null;
		return identifier.toIdentifier(identifier.getText().toLowerCase());
	}
}
