package youtube;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicRegexParser{
    private final Pattern p;
    private final String regex;
    private int group;

    public BasicRegexParser(String regex){
        super();
        p = Pattern.compile(regex);
        this.regex = regex;
        this.group =1;
    }
    public BasicRegexParser(String regex, int group){
        this(regex);
        this.group = group;
    }

    public String parse(String str) throws Exception {
        Matcher m = p.matcher(str);
        if(m.find()){
            if(m.groupCount() >= group){
                return m.group(group);
            }
        }
        throw  new Exception("Pattern not found.");
    }
}
