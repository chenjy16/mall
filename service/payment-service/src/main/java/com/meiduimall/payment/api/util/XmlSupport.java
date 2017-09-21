package com.meiduimall.payment.api.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 扩展XStream [CDATA[]]
 * 
 * @author Nico.Jiang
 * @since 2016.12.28
 *
 */
public class XmlSupport {

	public static <T> String outputXml(T paramObj, Class<T> paramClass) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(paramClass);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter stringWriter = new StringWriter();
		m.marshal(paramObj, stringWriter);
		String xmlString = stringWriter.toString();
		return xmlString;
	}

	@SuppressWarnings("unchecked")
	public static <T> T outputBean(String xmlData, Class<T> clazz) throws JAXBException {
		
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xmlData));
		
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String xmlData) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes());
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		inputStream.close();
		return map;
	}

	public static final XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;

				public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	
	/**
	 * 
	 * @param map 要转换的map对象
	 * @return  返回字符串
	 */
	
	public static String hashMapToJson(HashMap map) { 
        String string = "{"; 
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) { 
            Entry e = (Entry) it.next(); 
            string += "'" + e.getKey() + "':"; 
            if(!StringUtils.isBlank(String.valueOf(e.getValue())) && (String.valueOf(e.getValue()).contains("[") || String.valueOf(e.getValue()).contains("{"))){
            	 string +=  e.getValue() + ","; 
            }else{
            	
            	string += "'" + e.getValue() + "',"; 
            }
        } 
        string = string.substring(0, string.lastIndexOf(",")); 
        string += "}"; 
        return string; 
    }  
}
