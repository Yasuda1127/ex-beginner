package com.example.exbeginner.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbeginner.model.Member;

@Repository
public class MemberRepository {

  private static final String SEARCH_MEMBER = """
          SELECT
            name
          FROM
            members
          WHERE
          name LIKE :name
      """;

  @Autowired
  private NamedParameterJdbcTemplate template;

  // DBのカラムごとの情報をmemberにセットしている
  private static final RowMapper<Member> MEMBER_ROW_MAPPER = (rs, rowNum) -> {
    Member member = new Member();
    // member.setId(rs.getInt("id"));
    member.setName(rs.getString("name"));
    return member;
  };

  // 検索メソッド実装
  public List<Member> findByName(Member form) {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", "%" + form.getName() + "%");

    // 検索結果を格納
    List<Member> members = new ArrayList<>();
    try {
      members = template.query(SEARCH_MEMBER, params, MEMBER_ROW_MAPPER);
    } catch (EmptyResultDataAccessException ex) {
      System.out.println("結果が取得できませんでした: name=" + form.getName() + ":例外: " + ex);
    }
    System.out.println("form: " + form);
    System.out.println("members: " + members);
    return members;
  }
}
