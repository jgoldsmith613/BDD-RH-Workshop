package com.bdd.model;

import java.util.Set;

public class Room {

	private String name;
	private int capacity;
	private Set<String> tags;

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity( int capacity ) {
		this.capacity = capacity;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags( Set<String> tags ) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( tags == null ) ? 0 : tags.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Room other = (Room) obj;
		if ( capacity != other.capacity )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		if ( tags == null ) {
			if ( other.tags != null )
				return false;
		} else if ( !tags.equals( other.tags ) )
			return false;
		return true;
	}

}
