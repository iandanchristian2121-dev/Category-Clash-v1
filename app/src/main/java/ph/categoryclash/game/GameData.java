package ph.categoryclash.game;
import android.content.Context;import java.io.*;import java.nio.charset.StandardCharsets;import java.util.*;
final class GameData{
 static final class Category{final String difficulty,text;Category(String d,String t){difficulty=d;text=t;}}
 private GameData(){}
 static List<Category> load(Context c){ArrayList<Category> out=new ArrayList<>();HashSet<String> unique=new HashSet<>();try(BufferedReader r=new BufferedReader(new InputStreamReader(c.getResources().openRawResource(R.raw.category_bank),StandardCharsets.UTF_8))){String line;while((line=r.readLine())!=null){String[] f=line.split("\\t",2);if(f.length==2&&(f[0].equals("EASY")||f[0].equals("HARD"))){String text=f[1].trim(),key=f[0]+"|"+text.toLowerCase(Locale.ROOT);if(!unique.add(key))throw new IllegalStateException("Duplicate category: "+text);out.add(new Category(f[0],text));}}}catch(IOException e){throw new IllegalStateException("Unable to load categories",e);}if(out.size()!=500)throw new IllegalStateException("Category bank must contain exactly 500 unique entries");return out;}
}
