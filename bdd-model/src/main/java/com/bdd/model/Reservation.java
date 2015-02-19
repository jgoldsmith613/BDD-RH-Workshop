package com.bdd.model;

import org.joda.time.Interval;

public class Reservation {

	private Room room;
	private Interval interval;
	private String user;

	public Reservation( Room room, Interval interval, String user ) {
		super();
		this.room = room;
		this.interval = interval;
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom( Room room ) {
		this.room = room;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval( Interval interval ) {
		this.interval = interval;
	}

	public String getUser() {
		return user;
	}

	public void setUser( String user ) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( interval == null ) ? 0 : interval.hashCode() );
		result = prime * result + ( ( room == null ) ? 0 : room.hashCode() );
		result = prime * result + ( ( user == null ) ? 0 : user.hashCode() );
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
		Reservation other = (Reservation) obj;
		if ( interval == null ) {
			if ( other.interval != null )
				return false;
		} else if ( !interval.equals( other.interval ) )
			return false;
		if ( room == null ) {
			if ( other.room != null )
				return false;
		} else if ( !room.equals( other.room ) )
			return false;
		if ( user == null ) {
			if ( other.user != null )
				return false;
		} else if ( !user.equals( other.user ) )
			return false;
		return true;
	}

}
