package ua.realalpha.ragot.command;

import ua.realalpha.ragot.RagotHandle;

public interface RCommandManager {

    <T extends RagotHandle> T register(T t);

}
