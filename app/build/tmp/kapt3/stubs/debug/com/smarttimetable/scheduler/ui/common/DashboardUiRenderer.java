package com.smarttimetable.scheduler.ui.common;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062 \u0010\u0007\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t0\b2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n\u00a8\u0006\u0010"}, d2 = {"Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer;", "", "()V", "renderInfoCards", "", "container", "Landroid/widget/LinearLayout;", "items", "", "Lkotlin/Triple;", "", "Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardActions;", "emptyTitle", "emptySubtitle", "CardAction", "CardActions", "app_debug"})
public final class DashboardUiRenderer {
    @org.jetbrains.annotations.NotNull()
    public static final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer INSTANCE = null;
    
    private DashboardUiRenderer() {
        super();
    }
    
    public final void renderInfoCards(@org.jetbrains.annotations.NotNull()
    android.widget.LinearLayout container, @org.jetbrains.annotations.NotNull()
    java.util.List<kotlin.Triple<java.lang.String, java.lang.String, com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardActions>> items, @org.jetbrains.annotations.NotNull()
    java.lang.String emptyTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String emptySubtitle) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardAction;", "", "label", "", "onClick", "Lkotlin/Function0;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "getLabel", "()Ljava/lang/String;", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class CardAction {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
        
        public CardAction(@org.jetbrains.annotations.NotNull()
        java.lang.String label, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction copy(@org.jetbrains.annotations.NotNull()
        java.lang.String label, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardActions;", "", "editAction", "Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardAction;", "deleteAction", "(Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardAction;Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardAction;)V", "getDeleteAction", "()Lcom/smarttimetable/scheduler/ui/common/DashboardUiRenderer$CardAction;", "getEditAction", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class CardActions {
        @org.jetbrains.annotations.Nullable()
        private final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction editAction = null;
        @org.jetbrains.annotations.Nullable()
        private final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction deleteAction = null;
        
        public CardActions(@org.jetbrains.annotations.Nullable()
        com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction editAction, @org.jetbrains.annotations.Nullable()
        com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction deleteAction) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction getEditAction() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction getDeleteAction() {
            return null;
        }
        
        public CardActions() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardActions copy(@org.jetbrains.annotations.Nullable()
        com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction editAction, @org.jetbrains.annotations.Nullable()
        com.smarttimetable.scheduler.ui.common.DashboardUiRenderer.CardAction deleteAction) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}