package org.feature4j.config;

import org.feature4j.FeatureBundleProvider;
import org.feature4j.FeaturesContext;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/*
  * this is a bit closer to an integration test than a unit test.
  * see: src/test/resources/test_features.json
 */
public class JSONFeatureBundleProviderFactoryTest {

  private static final InputStream
      TEST_JSON =
      JSONFeatureBundleProviderFactoryTest.class.getResourceAsStream("/test_features.json");

  private static final FeaturesContext
      SESSION_0 =
      FeaturesContext.builder().bucketId(0).build();
  private static final FeaturesContext
      SESSION_1 =
      FeaturesContext.builder().bucketId(1).build();
  private static final FeaturesContext
      SESSION_2 =
      FeaturesContext.builder().bucketId(2).build();
  private static final FeaturesContext
      SESSION_3 =
      FeaturesContext.builder().bucketId(3).build();
  private static final FeaturesContext
      SESSION_99 =
      FeaturesContext.builder().bucketId(99).build();

  private FeatureBundleProviderFactory factory;

  @Before
  public void setUp() throws Exception {
    factory = new JSONFeatureBundleProviderFactory(TEST_JSON);
  }

  @Test
  public void testCreate() throws Exception {

    FeatureBundleProvider bundle = factory.create();

    final String featureKey = "unit.test.one";
    assertEquals("default-value", bundle.getFeatures(SESSION_0).getFeatures().get(featureKey));
    assertEquals("one-two", bundle.getFeatures(SESSION_1).getFeatures().get(featureKey));
    assertEquals("one-two", bundle.getFeatures(SESSION_2).getFeatures().get(featureKey));
    assertEquals("three", bundle.getFeatures(SESSION_3).getFeatures().get(featureKey));
    assertEquals("default-value", bundle.getFeatures(SESSION_99).getFeatures().get(featureKey));
    assertEquals(Boolean.TRUE,
        bundle.getFeatures(SESSION_0).getFeatures().get("unit.test.two"));
  }
}