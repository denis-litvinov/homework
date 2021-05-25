package epam.practical6.part6Right;

import java.io.File;
import java.util.HashMap;

public class ArgParser {
    private Option option = null;
    private static final HashMap<String, InputExecutor> TASK_MAP = new HashMap<>();

    static {
        TASK_MAP.put("frequency", Part61::execute);
        TASK_MAP.put("length", Part62::execute);
        TASK_MAP.put("duplicates", Part63::execute);
    }

    private String[] args;

    public ArgParser(String[] args) {
        this.args = args;
    }

    public void parse() {
        String file = null;
        InputExecutor task = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--input") || args[i].startsWith("-i") && Checks.INPUT.isCorrect(args[i + 1])) {
                file = args[++i];
            } else {
                if (args[i].equals("--task") || args[i].equals("-t") && Checks.TASK.isCorrect(args[i + 1])) {
                    task = TASK_MAP.get(args[++i]);
                }
            }
        }

        if (file == null || task == null) {
            throw new IllegalArgumentException("Wrong arguments!");
        }

        option = new Option(file, task);
    }

    private static class Option {
        private String file;
        private InputExecutor task;

        public Option(String file, InputExecutor task) {
            this.file = file;
            this.task = task;
        }

        private void execute() {
            task.execute(file);
        }
    }

    public void execute() {
        if (option == null) {
            return;
        }
        option.execute();
    }

    private interface InputExecutor {
        void execute(String s);
    }

    private interface ArgumentChecker {
        boolean isCorrect(String arg);
    }

    private enum Checks implements ArgumentChecker {
        INPUT {
            @Override
            public boolean isCorrect(String arg) {
                return new File(arg).isFile();
            }
        },
        TASK {
            @Override
            public boolean isCorrect(String arg) {
                return TASK_MAP.containsKey(arg);
            }

        }
    }

}
