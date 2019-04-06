package com.dardan.rrafshi.persistence;

import com.dardan.rrafshi.commons.exceptions.ApplicationException;


public final class DatabaseException
{
	private DatabaseException() {}


	public static class NoSuchRow extends ApplicationException
	{
		private static final long serialVersionUID = 1L;


		public NoSuchRow(final String message, final Throwable cause)
		{
			super(message, cause);
		}

		public NoSuchRow(final String message)
		{
			super(message);
		}
	}

	public static class TooManyRows extends ApplicationException
	{
		private static final long serialVersionUID = 1L;


		public TooManyRows(final String message, final Throwable cause)
		{
			super(message, cause);
		}

		public TooManyRows(final String message)
		{
			super(message);
		}
	}

	public static class RowAlreadyExist extends ApplicationException
	{
		private static final long serialVersionUID = 1L;


		public RowAlreadyExist(final String message, final Throwable cause)
		{
			super(message, cause);
		}

		public RowAlreadyExist(final String message)
		{
			super(message);
		}
	}
}
