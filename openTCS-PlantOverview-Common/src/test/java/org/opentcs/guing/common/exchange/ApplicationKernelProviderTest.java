/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.common.exchange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.opentcs.access.KernelServicePortal;
import org.opentcs.access.SharedKernelServicePortalProvider;
import org.opentcs.common.PortalManager;
import org.opentcs.util.gui.dialog.ConnectToServerDialog;

/**
 */
class ApplicationKernelProviderTest {

  /**
   * A (mocked) portal manager.
   */
  private PortalManager portalManager;
  /**
   * A (mocked) connection dialog.
   */
  private ConnectToServerDialog dialog;
  /**
   * A (mocked) configuration.
   */
  private ApplicationPortalProviderConfiguration appConfig;
  /**
   * The portal provider to be tested.
   */
  private SharedKernelServicePortalProvider portalProvider;

  @BeforeEach
  void setUp() {
    portalManager = mock(PortalManager.class);
    dialog = mock(ConnectToServerDialog.class);
    appConfig = mock(ApplicationPortalProviderConfiguration.class);
    portalProvider = new ApplicationPortalProvider(portalManager,
                                                   appConfig);
  }

  @Disabled
  @Test
  void shouldConnectOnClientRegistration() {
    when(portalManager.isConnected()).thenReturn(false, false, true);
    when(portalManager.getPortal()).thenReturn(mock(KernelServicePortal.class));

    portalProvider.register();

    verify(dialog).setVisible(true);
  }

  @Disabled
  @Test
  void shouldNotConnectIfAlreadyConnected() {
    when(portalManager.isConnected()).thenReturn(true);
    when(portalManager.getPortal()).thenReturn(mock(KernelServicePortal.class));

    portalProvider.register();

    verify(dialog, never()).setVisible(anyBoolean());
  }
}
