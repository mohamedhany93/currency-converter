package network

import android.content.Intent
import com.spotspal.fanartec.core.MyApplication
import org.json.JSONException
import retrofit2.Response

abstract class SafeApiRequest {


    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {

                val error = response.errorBody()!!.string()
                error.let {
                    try {
                        // handel error body here

                    } catch (e: JSONException) {

                    }
                }
            // add the message from error body json instead of this fixed message
            throw  ApiExceptions("Connection Error")
        }
    }
}