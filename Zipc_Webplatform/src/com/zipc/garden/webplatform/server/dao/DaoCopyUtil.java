package com.zipc.garden.webplatform.server.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the process of replicating Dao.
 */
public class DaoCopyUtil {

    /**
     * private constructor to block the instantiation from other class.
     */
    private DaoCopyUtil() {
    }

    /**
     * Duplicates the specified Dao class.
     * @param <T> Type of element to copy from
     * @param <U> Type of element to copy to
     * @param from Dao class from which to copy
     * @param to Dao class from Copy destination
     * @throws IllegalArgumentException
     */
    public static <T, U> void copy(T from, U to) throws IllegalArgumentException {
        for (Method getter : from.getClass().getMethods()) {
            String getterName = getter.getName();
            String setterName;
            if (getterName.startsWith("get")) {
                setterName = getterName.replaceFirst("get", "set");
            } else if (getterName.startsWith("is")) {
                setterName = getterName.replaceFirst("is", "set");
            } else {
                continue;
            }
            try {
                Method setter = to.getClass().getMethod(setterName, getter.getReturnType());
                try {
                    setter.invoke(to, new Object[] { getter.invoke(from, (Object[]) null) });
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
    }

    /**
     * Duplicates the list of specified Dao classes.
     * @param <T> Type of element to copy from
     * @param <U> Type of element to copy to
     * @param fromList List of Dao class from which to copy
     * @param toClass Class to store Dao to copy. This will be the type of the returned list.
     * @return List of duplicated Dao classes
     * @throws IllegalArgumentException
     */
    public static <T, U> List<U> copy(List<T> fromList, Class<U> toClass) throws IllegalArgumentException {
        List<U> toList = new ArrayList<U>();
        fromList.forEach(from -> {
            try {
                U to = toClass.newInstance();
                DaoCopyUtil.copy(from, to);
                toList.add(to);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalArgumentException(e);
            }
        });
        return toList;
    }
}
