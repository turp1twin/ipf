/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.core;

import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for XML validation profiles.
 * @author Dmytro Rud
 */
public class XmlValidationProfileRegistry extends IheRegistry {
    private static final Map<InteractionId, String[][]> REQUEST_VALIDATION_PROFILES =
            new HashMap<InteractionId, String[][]>();

    private static final Map<InteractionId, String[][]> RESPONSE_VALIDATION_PROFILES =
            new HashMap<InteractionId, String[][]>();

    private static final XmlValidationProfileRegistry INSTANCE = new XmlValidationProfileRegistry();


    private XmlValidationProfileRegistry() {
        super();
    }


    /**
     * Returns the registry instance (a singleton).
     */
    public static XmlValidationProfileRegistry instance() {
        return INSTANCE;
    }


    /**
     * Registers validation profile for an XML-based request.
     * @param interactionId
     *      interaction ID.
     * @param profile
     *      validation profile.
     */
    public synchronized void registerRequestValidationProfile(
            InteractionId interactionId,
            String[][] profile)
    {
        Validate.notNull(interactionId);
        //validateProfile(profile);
        REQUEST_VALIDATION_PROFILES.put(interactionId, profile);
    }


    /**
     * Registers validation profile for an XML-based response.
     * @param interactionId
     *      interaction ID.
     * @param profile
     *      validation profile.
     */
    public void registerResponseValidationProfile(
            InteractionId interactionId,
            String[][] profile)
    {
        Validate.notNull(interactionId);
        //validateProfile(profile);
        RESPONSE_VALIDATION_PROFILES.put(interactionId, profile);
    }


    private static void validateProfile(String[][] profile) {
        boolean valid = (profile != null);
        if (valid) {
            for (String[] row : profile) {
                if ((row == null) ||
                    (row.length != 3) ||
                    (isEmpty(row, 0)) ||
                    (isEmpty(row, 1) && isEmpty(row, 2)))
                {
                    valid = false;
                    break;
                }
            }
        }

        if (! valid) {
            throw new IllegalArgumentException("invalid XML validation profile");
        }
    }

    private static boolean isEmpty(String[] array, int index) {
        String s = array[index];
        return (s == null) || (s.trim().length() == 0);
    }


    /**
     * Registers validation profile for XML-based request and response.
     * @param interactionId
     *      interaction ID.
     * @param requestValidationProfile
     *      request validation profile.
     * @param responseValidationProfile
     *      response validation profile.
     */
    public void registerValidationProfiles(
            InteractionId interactionId,
            String[][] requestValidationProfile,
            String[][] responseValidationProfile)
    {
        registerRequestValidationProfile(interactionId, requestValidationProfile);
        registerResponseValidationProfile(interactionId, responseValidationProfile);
    }


    /**
     * @param interactionId
     *      interaction ID.
     * @return
     *      request validation profile for the given interaction ID.
     */
    public String[][] getRequestValidationProfiles(InteractionId interactionId) {
        return REQUEST_VALIDATION_PROFILES.get(interactionId);
    }


    /**
     * @param interactionId
     *      interaction ID.
     * @return
     *      response validation profile for the given interaction ID.
     */
    public String[][] getResponseValidationProfiles(InteractionId interactionId) {
        return RESPONSE_VALIDATION_PROFILES.get(interactionId);
    }

}
