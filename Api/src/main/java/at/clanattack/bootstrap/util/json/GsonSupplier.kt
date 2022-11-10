package at.clanattack.bootstrap.util.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder

@Deprecated("Use the GsonSupplier from XJKL", ReplaceWith("GsonSupplier", "at.clanattack.xjkl.json.GsonSupplier"))
class GsonSupplier {

    private val typeAdapters: MutableMap<Class<*>, Any> = HashMap()
    private var gson: Gson? = null

    fun getGson(): Gson {
        if (gson == null) {
            val gsonBuilder = GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()

            typeAdapters.forEach(gsonBuilder::registerTypeAdapter)

            gson = gsonBuilder.create()
        }

        return gson!!
    }

    fun registerTypeAdapter(clazz: Class<*>, typeAdapter: Any) {
        typeAdapters[clazz] = typeAdapter
        gson = null
    }
}