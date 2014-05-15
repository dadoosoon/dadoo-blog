/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.dao;

import im.dadoo.blog.domain.Article;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author codekitten
 */
@Repository
public class ArticleDao extends BaseDao<Article> {
  
  private static final String ADD_SQL = 
          "INSERT INTO t_article(title,html,text,publish_datetime,click) "
          + "VALUES(:title,:html,:text,:publish_datetime,:click)";
  
  private static final String UPDATE_CLICK_SQL = 
          "UPDATE t_article SET click=click+1 WHERE id=:id";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_article SET title=:title,html=:html,text=:text,publish_datetime=:publish_datetime "
          + "WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_article WHERE id=:id";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,title,html,text,publish_datetime,click "
          + "FROM t_article WHERE id=:id LIMIT 1";
  
  private static final String LIST_LIMIT_SQL = 
          "SELECT id,title,html,text,publish_datetime,click "
          + "FROM t_article ORDER BY publish_datetime DESC LIMIT :limit";
  
  private static final String LIST_PAGINATION_SQL = 
          "SELECT id,title,html,text,publish_datetime,click "
          + "FROM t_article ORDER BY publish_datetime DESC LIMIT :pagecount,:pagesize";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_article";
  
  private final RowMapper<Article> baseRowMapper;
  
  public ArticleDao() {
    super(Article.class);
    this.baseRowMapper = new BaseRowMapper();
  }
  
  @Override
  public Article add(Article article) {
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("title", article.getTitle());
    sps.addValue("html", article.getHtml());
    sps.addValue("text", article.getText());
    sps.addValue("publish_datetime", article.getPublishDatetime());
    sps.addValue("click", article.getClick());
    this.jdbcTemplate.update(ADD_SQL, sps, holder);
    article.setId(holder.getKey().intValue());
    return article;
  }
  
  @Override
  public Article update(Article article) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", article.getId());
    sps.addValue("title", article.getTitle());
    sps.addValue("html", article.getHtml());
    sps.addValue("text", article.getText());
    sps.addValue("publish_datetime", article.getPublishDatetime());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return article;
  }
  
  public void click(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(UPDATE_CLICK_SQL, sps);
  }
  
  @Override
  public void deleteById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }
  
  @Override
  public Article findById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Article> articles = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.baseRowMapper);
    if (articles != null && !articles.isEmpty()) {
      return articles.get(0);
    } else {
      return null;
    }
  }
  
  public List<Article> list(Integer limit) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("limit", limit);
    List<Article> articles = this.jdbcTemplate.query(LIST_LIMIT_SQL, sps, this.baseRowMapper);
    return articles;
  }
  
  @Override
  public List<Article> list(Integer pagecount, Integer pagesize) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("pagecount", pagecount);
    sps.addValue("pagesize", pagesize);
    List<Article> articles = this.jdbcTemplate.query(LIST_PAGINATION_SQL, sps, this.baseRowMapper);
    return articles;
  }
  
  @Override
  public Serializable size() {
    return (Serializable)this.jdbcTemplate.queryForObject(SIZE_SQL, 
            (SqlParameterSource)null, new RowMapper<Integer>() {
      @Override
      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("size");
      }
    });
  }
  
  private class BaseRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
      Article article = new Article();
      article.setId(rs.getInt("id"));
      article.setTitle(rs.getString("title"));
      article.setHtml(rs.getString("html"));
      article.setText(rs.getString("text"));
      article.setPublishDatetime(rs.getLong("publish_datetime"));
      article.setClick(rs.getInt("click"));
      return article;
    }
  }
}
