package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 
 * 管理者テーブルを操作するリポジトリ.
 * 
 * @author suzukikunpei
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 
	 * RowMapperの定義.
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));

		return administrator;
	};

	/**
	 * 管理者情報を挿入する.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		String insertSql = "INSERT INTO administrators(name,mail_address,password) VALUES(:name,:mailAddress,:password)";
		template.update(insertSql, param);
	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報
	 * 
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {



		String selectSql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mailAddress AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);

		try {
			Administrator administrator = template.queryForObject(selectSql, param, ADMINISTRATOR_ROW_MAPPER);
			return administrator;
		} catch (Exception e) {
			return null;

		}
	}

}
