class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            return "DENIED";
        }

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (String[] attempt : attempts) {
            String modifier = attempt[0];
            String context = attempt[1];

            String result = classifyAccess(modifier, context);

            for (int i = 0; i < 4; i++) {
                if (modifier.equals(modifiers[i])) {
                    if (result.equals("ALLOWED"))
                        allowed[i]++;
                    else
                        denied[i]++;
                }
            }
        }

        return modifiers[0] + ": " + allowed[0] + " allowed / " + denied[0] + " denied | " +
               modifiers[1] + ": " + allowed[1] + " allowed / " + denied[1] + " denied | " +
               modifiers[2] + ": " + allowed[2] + " allowed / " + denied[2] + " denied | " +
               modifiers[3] + ": " + allowed[3] + " allowed / " + denied[3] + " denied";
    }
}
