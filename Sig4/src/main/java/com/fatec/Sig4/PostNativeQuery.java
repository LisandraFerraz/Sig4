package com.fatec.Sig4;


import io.github.gasparbarancelli.NativeQuery;
import io.github.gasparbarancelli.NativeQueryParam;


public interface PostNativeQuery<PEDIDO> extends NativeQuery {

    <PEDIDO> void posts(@NativeQueryParam("clienteId") Long authorId, @NativeQueryParam("tagId") Long pedidoId);

}
