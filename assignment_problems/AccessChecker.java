class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            return "DENIED";
        }

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            if (classifyAccess(modifier, context).equals("DENIED")) {
                return modifier + " via " + context +
                       " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }
}
