/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.dao;

import im.dadoo.blog.domain.Link;
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
public class LinkDao extends BaseDao<Link>{
  
  private static final String ADD_SQL = 
          "INSERT INTO t_link(name,url,description) VALUES(:name,:url,:description)";
  
  private static final String UPDATE_SQL = 
          "UPDATE t_link SET name=:name,url=:url,description=:description WHERE id=:id";
  
  private static final String DELETE_BY_ID_SQL = 
          "DELETE FROM t_link WHERE id=:id";
  
  private static final String FIND_BY_ID_SQL = 
          "SELECT id,name,url,description FROM t_link WHERE id=:id LIMIT 1";
  
  private static final String FIND_BY_NAME_SQL = 
          "SELECT id,name,url,description FROM t_link WHERE name=:name LIMIT 1";
  
  private static final String LIST_SQL = 
          "SELECT id,name,url,description FROM t_link ORDER BY name ASC";
  
  private static final String SIZE_SQL = 
          "SELECT count(*) AS size FROM t_link";
  
  private final RowMapper<Link> baseRowMapper;
  
  public LinkDao() {
    super(Link.class);
    this.baseRowMapper = new BaseRowMapper();
  }
  
  @Override
  public Link add(Link link) {
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", link.getName());
    sps.addValue("url", link.getUrl());
    sps.addValue("description", link.getDescription());
    this.jdbcTemplate.update(ADD_SQL, sps, holder);
    link.setId(holder.getKey().intValue());
    return link;
  }
  
  @Override
  public Link update(Link link) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", link.getId());
    sps.addValue("name", link.getName());
    sps.addValue("url", link.getUrl());
    sps.addValue("description", link.getDescription());
    this.jdbcTemplate.update(UPDATE_SQL, sps);
    return link;
  }
  
  @Override
  public void deleteById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(DELETE_BY_ID_SQL, sps);
  }
  
  @Override
  public Link findById(Serializable id) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<Link> links = this.jdbcTemplate.query(FIND_BY_ID_SQL, sps, this.baseRowMapper);
    if (links != null && !links.isEmpty()) {
      return links.get(0);
    } else {
      return null;
    }
  }
  
  public Link findByName(String name) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("name", name);
    List<Link> links = this.jdbcTemplate.query(FIND_BY_NAME_SQL, sps, this.baseRowMapper);
    if (!links.isEmpty()) {
      return links.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<Link> list() {
    List<Link> links = this.jdbcTemplate.query(LIST_SQL, this.baseRowMapper);
    return links;
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
  
  private class BaseRowMapper implements RowMapper<Link> {
    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
      Link link = new Link();
      link.setId(rs.getInt("id"));
      link.setName(rs.getString("name"));
      link.setUrl(rs.getString("url"));
      link.setDescription(rs.getString("description"));
      return link;
    }
  }
}
