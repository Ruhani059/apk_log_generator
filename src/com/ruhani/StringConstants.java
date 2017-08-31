package com.ruhani;

public class StringConstants {

    public final String const_string = "const-string";
    public final String TAG = "\"Ruhani\"";
    public final String invoke_static = "invoke-static";
    public final String move_result = "move-result";
    public final String Log_d = "Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I";
    public String tag_variable;
    public String tag_String;
    public String message_variable;
    public String message_string;
    public String fullLogMessage;

    public StringConstants(String tag_variable, String tag_String, String message_variable, String message_string) {
        this.tag_variable = tag_variable;
        this.tag_String = "\"" + tag_String + "\"";
        this.message_variable = message_variable;
        this.message_string = "\"" + message_string + "\"";
    }

    public StringConstants(int variable_number, String tag_String, String message_string) {
        this.tag_variable = "v" + String.valueOf(variable_number);
        this.tag_String = "\"" + tag_String + "\"";
        this.message_variable = "v" + String.valueOf(variable_number+1);
        this.message_string = "\"" + message_string + "\"";

        this.fullLogMessage = this.createLogMessage();
    }

    private String createLogMessage()
    {
        StringBuffer msgBuffer = new StringBuffer();
        // "    const-string v0, Tag\n"
        msgBuffer.append("    ").append(const_string).append(" ").append(tag_variable).append(", ").append(tag_String).append("\n");
        // "    const-string v0, Message\n"
        msgBuffer.append("    ").append(const_string).append(" ").append(message_variable).append(", ").append(message_string).append("\n");
        // "    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I\n"
        msgBuffer.append("    ").append(invoke_static).append(" {").append(tag_variable).append(", ").append(message_variable)
                .append("}, ").append(Log_d).append("\n");
        // "    move-result v0\n"
        msgBuffer.append("    ").append(move_result).append(" ").append(tag_variable).append("\n");
        return msgBuffer.toString();
    }
}
