package com.example.justnews.APIS.Model.NewsModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.justnews.APIS.Model.SourcesItem;
import com.google.gson.annotations.SerializedName;
@Entity(foreignKeys = @ForeignKey(entity = SourcesItem.class,
                                    parentColumns = "id", childColumns = "sourceId"))
public class ArticlesItem{

	@ColumnInfo
	@SerializedName("publishedAt")
	private String publishedAt;

	@ColumnInfo
	@SerializedName("author")
	private String author;

	@ColumnInfo
	@SerializedName("urlToImage")
	private String urlToImage;

	@ColumnInfo
	@SerializedName("description")
	private String description;

	@Ignore
	@SerializedName("source")
	private SourcesItem source;

	private String sourceId;
	private String sourceName;

	@ColumnInfo
	@SerializedName("title")
	private String title;

	@PrimaryKey
	@ColumnInfo
	@NonNull
	@SerializedName("url")
	private String url;

	@ColumnInfo
	@SerializedName("content")
	private String content;

	public ArticlesItem() {
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setUrlToImage(String urlToImage){
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSource(SourcesItem source){
		this.source = source;
	}

	public SourcesItem getSource(){
		return source;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"ArticlesItem{" + 
			"publishedAt = '" + publishedAt + '\'' + 
			",author = '" + author + '\'' + 
			",urlToImage = '" + urlToImage + '\'' + 
			",description = '" + description + '\'' + 
			",source = '" + source + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}