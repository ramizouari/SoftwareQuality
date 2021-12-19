package SRP.example;

import javax.swing.text.html.HTML;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * La creation de Tags est repetitive
 * On utilise cette classe pour simplifier cette tâche dans le EmployeeParser
 * Le design pattern "Builder" est utilisé
 */
public class TagBuilder {

    private Map<String, String> _attributes = new HashMap<String, String>();
    private String _tag = "tag";
    private String _content = "";

    public TagBuilder(TagBuilder tagBuilder) {
        this._tag=  tagBuilder._tag ;
        this._content = tagBuilder._content;
        this._attributes = tagBuilder._attributes;
    }

    public TagBuilder() {
    }

    public TagBuilder setTag(String tag) {
        _tag = tag ;
        return this;
    }

    public TagBuilder setContent(String content) {
        _content = content;
        return this;
    }

    public TagBuilder setContent(int content) {
        _content = String.valueOf(content);
        return this;
    }

    public TagBuilder setContent(long content) {
        _content = String.valueOf(content);
        return this;
    }

    public TagBuilder setContent(TagBuilder tagBuilder) {
        _content = tagBuilder.toString();
        return this;
    }

    public void clearAttributes() {
        _attributes.clear();
    }

    public TagBuilder setAttribute(String attribute, String value) {
        clearAttributes();
        _attributes.put(attribute, value);
        return this;
    }

    public TagBuilder addAttribute(String attribute, String value) {
        _attributes.put(attribute, value);
        return this;
    }

    private String getAttributes() {
        String attrs = "" ;

        Iterator attributesIterator = _attributes.entrySet().iterator();

        while (attributesIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)attributesIterator.next();
            String attribute  = mapElement.getKey().toString();
            String value = (String)mapElement.getValue();
            attrs += String.format(" %s='%s'",attribute,value);
        }
        return attrs;
    }

    public String getInTag() {
        return String.format("%s%s%s",getOpeningTag(),_content,getClosingTag());
    }

    public String getOpeningTag(){
        return String.format("<%s%s>", _tag, getAttributes());
    }
    public String getClosingTag() {
        return String.format("</%s>", _tag);
    }

    @Override
    public String toString() {
        return this.getInTag();
    }
}
