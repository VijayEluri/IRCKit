/*
 * IRCPrefix.java
 * IRCKit
 *
 * Created by tarchan on 2010/03/17.
 * Copyright (c) 2010 tarchan. All rights reserved.
 */
package com.mac.tarchan.irc.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IRCメッセージのプレフィックスを、サーバ名、ニックネーム、ユーザ名、ホスト名に分割する機能を提供します。
 */
public class IRCPrefix
{
	/** プレフィックス形式 */
	protected static Pattern prefixPattern = Pattern.compile("([^!]+)(!.+)?(@.+)?");

	/** プレフィックス */
	protected String prefix;

	/** メッセージ作成時間 */
	protected long when;

	/** ニックネーム */
	protected String nick;

	/** ユーザ名 */
	protected String user;

	/** ホスト名 */
	protected String host;

	@Deprecated
	public IRCPrefix(String prefix)
	{
		this(prefix, System.currentTimeMillis());
	}

	/**
	 * プレフィックスを、サーバ名、ニックネーム、ユーザ名、ホスト名に分割します。
	 * 
	 * @param prefix プレフィックス
	 */
	public IRCPrefix(String prefix, long when)
	{
		this.when = when;
		if (prefix == null) return;

		this.prefix = prefix;
		Matcher m = prefixPattern.matcher(prefix);
		if (m.find())
		{
			nick = m.group(1);
			user = m.group(2);
			host = m.group(3);
		}
		else
		{
			throw new IllegalArgumentException("プレフィックスが不正です。: " + prefix);
		}
	}

	/**
	 * プレフィックスを返します。
	 * 
	 * @return プレフィックス
	 */
	public String getPrefix()
	{
		return prefix;
	}

	/**
	 * メッセージの作成時間を返します。
	 * 
	 * @return メッセージの作成時間
	 */
	public long getWhen()
	{
		return when;
	}

	/**
	 * サーバ名またはニックネームを返します。
	 * 
	 * @return サーバ名またはニックネーム
	 */
	public String getNick()
	{
		return nick;
	}

	/**
	 * ユーザ名を返します。
	 * 
	 * @return ユーザ名
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * ホスト名を返します。
	 * 
	 * @return ホスト名
	 */
	public String getHost()
	{
		return host;
	}

	@Override
	public String toString()
	{
		return prefix;
	}
}