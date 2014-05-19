/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.dao;

import im.dadoo.blog.domain.TagArticle;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author codekitten
 */
@Repository
public class TagArticleDao extends BaseDao<TagArticle>{
  
  
  private static final String ADD_SQL = 
          "INSERT INTO t_tag_article(tag_id,article_id) VALUES(:tag_id,:article_id)";
  
  private static final String DELETE_BY_TAG_ID_SQL = 
          "DELETE FROM t_tag_article WHERE tag_id=:tag_id";
  
  private static final String DELETE_BY_ARTICLE_ID_SQL = 
          "DELETE FROM t_tag_article WHERE article_id=:article_id";
  
  private static final String SIZE_BY_TAG_ID_SQL = 
          "SELECT count(*) AS size FROM t_tag_article WHERE tag_id=:tag_id";
  
  private final RowMapper<TagArticle> baseRowMapper;
  
  public TagArticleDao() {
    super(TagArticle.class);
    this.baseRowMapper = new BaseRowMapper();
  }
  
  @Override
  public TagArticle add(TagArticle ta) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", ta.getTagId());
    sps.addValue("article_id", ta.getArticleId());
    this.jdbcTemplate.update(ADD_SQL, sps);
    return ta;
  }
  
  public void deleteByTagId(Integer tagId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    this.jdbcTemplate.update(DELETE_BY_TAG_ID_SQL, sps);
  }
  
  public void deleteByArticleId(Integer articleId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("article_id", articleId);
    this.jdbcTemplate.update(DELETE_BY_ARTICLE_ID_SQL, sps);
  }
  
  public Serializable sizeByTagId(Integer tagId) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("tag_id", tagId);
    return (Serializable)this.jdbcTemplate.queryForObject(SIZE_BY_TAG_ID_SQL, 
            sps, new RowMapper<Integer>() {
      @Override
      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("size");
      }
    });
  }
  
  private class BaseRowMapper implements RowMapper<TagArticle> {
    @Override
    public TagArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
      TagArticle ta = new TagArticle();
      ta.setTagId(rs.getInt("tag_id"));
      ta.setArticleId(rs.getInt("article_id"));
      return ta;
    }
  }
}
