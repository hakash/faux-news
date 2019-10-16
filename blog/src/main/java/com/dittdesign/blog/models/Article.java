package com.dittdesign.blog.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = true, unique = true)
	private String slug;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = true)
	private String imageUrl;
	
	@Column(nullable = true)
	private String altText;
	
	@Column(nullable = false)
	private String catchline;
	
	@Column(nullable = false, columnDefinition="varchar(255) DEFAULT 'article'")
	private String type = "article";
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String body;
	
	@Column(nullable = false, columnDefinition="TEXT")
	private String ingress;

	@Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date(); 
	
	public String getSlug() {
		return String.format("%s-%l",title.toLowerCase().replace(" ", "-"),this.id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}
	
	public String getBodyAsHtml() {
		MutableDataHolder options = new MutableDataSet();
		options.setFrom(ParserEmulationProfile.MARKDOWN);
		Parser parser = Parser.builder(options).build();
		HtmlRenderer renderer = HtmlRenderer.builder(options).build();
		Node document = parser.parse(this.body);
		String html = renderer.render(document);
		return html;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIngress() {
		return ingress;
	}

	public void setIngress(String ingress) {
		this.ingress = ingress;
	}

	public Date getCreated() {
		return created;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public String getCatchline() {
		return catchline;
	}

	public void setCatchline(String catchline) {
		this.catchline = catchline;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", slug=" + slug + ", title=" + title + ", author=" + author + ", imageUrl="
				+ imageUrl + ", altText=" + altText + ", catchline=" + catchline + ", type=" + type + ", body=" + body
				+ ", ingress=" + ingress + ", created=" + created + "]";
	}
}
