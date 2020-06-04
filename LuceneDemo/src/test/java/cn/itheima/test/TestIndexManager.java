package cn.itheima.test;

import cn.itheima.dao.SkuDao;
import cn.itheima.dao.SkuDaoImpl;
import cn.itheima.pojo.Sku;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 索引维护
 */
public class TestIndexManager {

    /**
     * 创建索引
     */
    @Test
    public void createIndexTest() throws Exception{
        //1.采集数据
        //1. 采集数据
        SkuDao skuDao = new SkuDaoImpl();
        List<Sku> skuList = skuDao.querySkuList();

        //文档集合
        List<Document> docList = new ArrayList<>();

        for (Sku sku : skuList) {
            //2. 创建文档对象
            Document document = new Document();
            document.add(new StringField("id", sku.getId(), Field.Store.YES));
            document.add(new TextField("name", sku.getName(), Field.Store.YES));
            //分词加索引
            document.add(new IntPoint("price", sku.getPrice()));
            //存储
            document.add(new StoredField("price", sku.getPrice()));
            //document.add(new TextField("price", sku.getPrice(), Field.Store.YES));
            document.add(new StoredField("image", sku.getImage()));
            document.add(new StringField("categoryName", sku.getCategoryName(), Field.Store.YES));
            document.add(new StringField("brandName", sku.getBrandName(), Field.Store.YES));

            //将文档对象放入到文档集合中
            docList.add(document);
        }
        //3. 创建分词器, StandardAnalyzer标准分词器, 对英文分词效果好, 对中文是单字分词, 也就是一个字就认为是一个词.
        Analyzer analyzer = new StandardAnalyzer();
        //4. 创建Directory目录对象, 目录对象表示索引库的位置
        Directory dir = FSDirectory.open(Paths.get("E:\\dir"));
        //5. 创建IndexWriterConfig对象, 这个对象中指定切分词使用的分词器
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //6. 创建IndexWriter输出流对象, 指定输出的位置和使用的config初始化对象
        IndexWriter indexWriter = new IndexWriter(dir, config);
        //7. 写入文档到索引库
        for (Document doc : docList) {
            indexWriter.addDocument(doc);
        }
        //8. 释放资源
        indexWriter.close();
    }



    @Test
    public void updateIndexTest() throws Exception{
        //1.采集数据
        //1. 采集数据
        SkuDao skuDao = new SkuDaoImpl();
        List<Sku> skuList = skuDao.querySkuList();

        //文档集合
        List<Document> docList = new ArrayList<>();

        for (Sku sku : skuList) {
            //2. 创建文档对象
            Document document = new Document();
            document.add(new StringField("id", sku.getId(), Field.Store.YES));
            document.add(new TextField("name", sku.getName(), Field.Store.YES));
            //分词加索引
            document.add(new IntPoint("price", sku.getPrice()));
            //存储
            document.add(new StoredField("price", sku.getPrice()));
            //document.add(new TextField("price", sku.getPrice(), Field.Store.YES));
            document.add(new StoredField("image", sku.getImage()));
            document.add(new StringField("categoryName", sku.getCategoryName(), Field.Store.YES));
            document.add(new StringField("brandName", sku.getBrandName(), Field.Store.YES));

            //将文档对象放入到文档集合中
            docList.add(document);
        }
        //3. 创建分词器, StandardAnalyzer标准分词器, 对英文分词效果好, 对中文是单字分词, 也就是一个字就认为是一个词.
        Analyzer analyzer = new StandardAnalyzer();
        //4. 创建Directory目录对象, 目录对象表示索引库的位置
        Directory dir = FSDirectory.open(Paths.get("E:\\dir"));
        //5. 创建IndexWriterConfig对象, 这个对象中指定切分词使用的分词器
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //6. 创建IndexWriter输出流对象, 指定输出的位置和使用的config初始化对象
        IndexWriter indexWriter = new IndexWriter(dir, config);
        //7. 写入文档到索引库
        for (Document doc : docList) {
            indexWriter.addDocument(doc);
        }
        //8. 释放资源
        indexWriter.close();
    }

}

