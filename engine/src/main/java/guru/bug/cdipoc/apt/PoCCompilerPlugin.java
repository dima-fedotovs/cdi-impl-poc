package guru.bug.cdipoc.apt;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;
import com.sun.source.util.*;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

public class PoCCompilerPlugin implements Plugin {
    private Context ctx;
    @Override
    public String getName() {
        return "PoCCompilerPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        ctx = ((BasicJavacTask) task).getContext();
        System.out.println("COMPILER PLUGIN INIT");
        task.addTaskListener(new TaskListener() {
            @Override
            public void started(TaskEvent e) {
                System.out.println("started: " + e);
                if (e.getKind() == TaskEvent.Kind.ANALYZE) {
                    addLog(e.getCompilationUnit());
                }
            }

            @Override
            public void finished(TaskEvent e) {
                System.out.println("finished: " + e);
            }
        });
    }

    private void addLog(CompilationUnitTree e) {
        e.accept(new TreeScanner<Void, Void>() {
            @Override
            public Void visitMethod(MethodTree node, Void aVoid) {
                var body = (JCTree.JCBlock) node.getBody();
                if (node.getName().contentEquals("main")) {
                    System.out.println("MAIN METHOD STATS: [" + body.stats + "]");
                    explore(body.stats);
                }
                return super.visitMethod(node, aVoid);
            }
        }, null);
//        var factory = TreeMaker.instance(ctx);
//        var symbolsTable = Names.instance(ctx);
//        var systemName = symbolsTable.fromString(System.class.getName());
//        var systemType = factory.Ident(systemName).type;
//        factory.E
//        e.accept(new TreeScanner<Void, Void>() {
//            @Override
//            public Void visitMethod(MethodTree node, Void aVoid) {
//                var log = factory.at(((JCTree)node).pos)
//                        .ClassLiteral(systemType)
//                var body = (JCTree.JCBlock) node.getBody();
//                body.stats = body.stats.prepend(log);
//                return super.visitMethod(node, aVoid);
//            }
//        }, null);
    }

    private void explore(List<JCTree.JCStatement> stats) {
        for (var s : stats) {
            explore(s);
        }
    }

    private void explore(JCTree s) {
        s.accept(new TreeScanner<Void, Void>() {
            @Override
            public Void scan(Tree tree, Void aVoid) {
                System.out.println(tree);
                var t = (JCTree) tree;
                explore(t);
                return super.scan(tree, aVoid);
            }
        }, null);
    }
}
