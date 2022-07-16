package blue.lhf.bytepaper.library.syntax.direction.egocentric;

import blue.lhf.bytepaper.library.syntax.direction.egocentric.Egocentric;
import mx.kenzie.foundation.MethodBuilder;
import mx.kenzie.foundation.Type;
import org.byteskript.skript.api.Library;
import org.byteskript.skript.api.syntax.Literal;
import org.byteskript.skript.compiler.Context;
import org.byteskript.skript.compiler.Pattern;
import org.byteskript.skript.lang.element.StandardElements;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static mx.kenzie.foundation.WriteInstruction.loadConstant;

public class LiteralEgocentric extends Literal<Egocentric> {
    private static final Map<String, Egocentric> parseMap = new HashMap<>();

    static {
        for (Egocentric dir : Egocentric.values()) {
            parseMap.put(dir.name().toLowerCase(Locale.ROOT), dir);
        }
    }

    public LiteralEgocentric(Library library) {
        super(library, StandardElements.EXPRESSION, "(" + parseMap.keySet()
                .stream()
                .map(java.util.regex.Pattern::quote)
                .map(s -> s.replace("\\", "\\\\"))
                .collect(Collectors.joining("|")) + ")");
    }

    @Override
    public Pattern.Match match(String thing, Context context) {
        return super.match(thing, context);
    }

    @Override
    public Type getReturnType() {
        return new Type(Egocentric.class);
    }

    public void compile(Context context, Pattern.Match match) throws Throwable {
        String string = match.matcher().group();

        assert string.length() > 1;

        MethodBuilder method = context.getMethod();

        method.writeCode(loadConstant(string));
        writeCall(method, findMethod(getClass(), "fromString", String.class), context);
    }

    public static Egocentric fromString(String s) {
        return parseMap.get(s);
    }

    @Override
    public Egocentric parse(String s) {
        return parseMap.get(s);
    }
}