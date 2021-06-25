package com.spotspal.fanartec.core

import kotlin.collections.ArrayList

data class BaseResponse<T: Any> (
        val keyfortest: Int  ,
        val data: T)
{

}

