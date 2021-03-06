package Engine;

import Data.*;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.CpxNumVar;
import ilog.cplex.IloCplex;
import ilog.cplex.IloCplexModeler;

import java.util.logging.Level;
import java.util.logging.Logger;

import static ilog.cplex.IloCplex.DoubleParam.EpGap;

public class NewSolution_CPLEX {

    private final Network Network;
    private final int LARGE_NUMBER = 9999999;
    public IloCplex CPLEX;
    public double[] R;
    public double[][] C;
    public int[] W;
    public double[][] U;
    public double[] UE;
    public double[] UL;
    public double[] UP;
    public IloNumVar[][][][] x;
    public IloNumVar[][][] y;
    public IloNumVar[][] d;
    public IloNumVar[][] u;
    public int[][][][] X_Value;
    public int[][][] Y_Value;
    public int[][] D_Value;
    public int[][] U_Value;
    private int VariableIndex = 0;
    private IloLinearNumExpr constraintExpr;

    public NewSolution_CPLEX(Network Network) {
        this.Network = Network;
        InitializeCPLEX();
        SetParameters();
        SetVariable();
        SetConstraints();

        SetObjectiveFuntion();

        if (SolveProblem()) {
            ExtractResults();
        }

    }

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~~  InitializeCPLEX ~~~~~~~~~~~~~~~~~~~~~~~~~~ ">
    private void InitializeCPLEX() {

        try {
            CPLEX = new IloCplex();
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Auto);
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Primal);
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Dual);
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Barrier);
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Barrier);
//            CPLEX.setParam(IloCplex.IntParam.BarCrossAlg, IloCplex.Algorithm.None);
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Network);
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Sifting);
//            CPLEX.setParam(IloCplex.IntParam.RootAlg, IloCplex.Algorithm.Concurrent);
//            CPLEX.setParam(IloCplex.IntParam.Threads, 4);
            CPLEX.setParam(EpGap, 0.01);
            CPLEX.setParam(IloCplex.DoubleParam.TiLim, 21600);
        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~~  Creating Constant Parameters ~~~~~~~~~~~~~~~~~~~~~~~~~~ ">
    private void Create_R_Parameter() {
        int PassengersCount = Network.Passengers.size();
        R = new double[PassengersCount];
        for (int p = 0; p < PassengersCount; p++) {
            R[p] = Network.Passengers_SortedByIndex.get(p).Revenue;
        }
    }

    private void Create_C_Parameter() {
        int NodesCount = Network.Nodes.size();
        C = new double[NodesCount][NodesCount];
        Route Route;
        for (int OriginIndex = 0; OriginIndex < NodesCount; OriginIndex++) {
            long Origin = Network.Nodes_SortedByIndex.get(OriginIndex).ID;
            for (int DestinationIndex = 0; DestinationIndex < NodesCount; DestinationIndex++) {
                long Destination = Network.Nodes_SortedByIndex.get(DestinationIndex).ID;
                if (Origin == Destination) {
                    C[OriginIndex][DestinationIndex] = 0;
                    continue;
                }
                Route = Network.Routes.get(Origin).get(Destination);
                try {
                    C[OriginIndex][DestinationIndex] = Route.TotalOperationCost;
                } catch (Exception e) {

                }
            }
        }
    }

    private void Create_W_Parameter() {
        int TaxisCount = Network.Taxis.size();
        W = new int[TaxisCount];

        for (int t = 0; t < TaxisCount; t++) {
            W[t] = Network.Taxis_SortedByIndex.get(t).Capacity;
        }
    }

    private void Create_U_Parameter() {
        int NodesCount = Network.Nodes.size();
        U = new double[NodesCount][NodesCount];

        Route Route;
        for (int OriginIndex = 0; OriginIndex < NodesCount; OriginIndex++) {
            long Origin = Network.Nodes_SortedByIndex.get(OriginIndex).ID;
            for (int DestinationIndex = 0; DestinationIndex < NodesCount; DestinationIndex++) {
                long Destination = Network.Nodes_SortedByIndex.get(DestinationIndex).ID;
                if (Origin == Destination) {
                    U[OriginIndex][DestinationIndex] = 0;
                    continue;
                }
                Route = Network.Routes.get(Origin).get(Destination);
                U[OriginIndex][DestinationIndex] = Route.TotalTravelTime;
            }
        }
    }

    private void Create_UE_Parameter() {
        int Passengerscount = Network.Passengers.size();
        UE = new double[Passengerscount];

        for (int p = 0; p < Passengerscount; p++) {
            UE[p] = Network.Passengers_SortedByIndex.get(p).PickupTime;
        }
    }

    private void Create_UL_Parameter() {
        int Passengerscount = Network.Passengers.size();
        UL = new double[Passengerscount];

        for (int p = 0; p < Passengerscount; p++) {
            UL[p] = Network.Passengers_SortedByIndex.get(p).DropoffTime;
        }
    }

    private void Create_UP_Parameter() {
        int Passengerscount = Network.Passengers.size();
        UP = new double[Passengerscount];

        for (int p = 0; p < Passengerscount; p++) {
            UP[p] = Network.Passengers_SortedByIndex.get(p).RideDuration;
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~~  Creating Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    private void SetParameters() { // Constants
        Create_R_Parameter();
        Create_C_Parameter();
        Create_W_Parameter();
        Create_U_Parameter();
        Create_UE_Parameter();
        Create_UL_Parameter();
        Create_UP_Parameter();

    }

    private void Create_x_Variable() {
        int NodesCount = Network.Nodes.size();
        int PassengersCount = Network.Passengers.size();
        int TaxisCount = Network.Taxis.size();

        x = new IloNumVar[PassengersCount][TaxisCount][NodesCount][NodesCount];
        X_Value = new int[PassengersCount][TaxisCount][NodesCount][NodesCount];
        for (int p = 0; p < PassengersCount; p++) {
            for (int t = 0; t < TaxisCount; t++) {
                for (int i = 0; i < NodesCount; i++) {
                    Node NodeI = Network.Nodes_SortedByIndex.get(i);
                    for (int j = 0; j < NodesCount; j++) {
                        Node NodeJ = Network.Nodes_SortedByIndex.get(j);
                        String Name = String.format("x(P%d)(t%d)(i%d)(j%d)", p, t, i, j);
                        double LowerBound = 0;
                        double UpperBound = 1;
                        try {
                            if (!NodeI.AdjcantNodes.contains(NodeJ.ID)) {
                                x[p][t][i][j] = CPLEX.numVar(LowerBound, LowerBound, IloNumVarType.Int, Name);
                                if (i == 32 && j == 26) {
                                    System.err.println(String.format("x[%d][%d][%d][%d] = 0", p, t, i, j));
                                }
                            } else {
                                x[p][t][i][j] = CPLEX.numVar(LowerBound, UpperBound, IloNumVarType.Int, Name);
                                if (i == 32 && j == 26) {
                                    System.err.println(String.format("x[%d][%d][%d][%d] Between 0 and 1", p, t, i, j));
                                }
                            }
                        } catch (IloException ex) {
                            Logger.getLogger(RoutingProblem_CPLEX.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    private void Create_y_Variable() {
        int NodesCount = Network.Nodes.size();
        int TaxisCount = Network.Taxis.size();

        y = new IloNumVar[TaxisCount][NodesCount][NodesCount];
        Y_Value = new int[TaxisCount][NodesCount][NodesCount];
        for (int t = 0; t < TaxisCount; t++) {
            for (int i = 0; i < NodesCount; i++) {
                Node NodeI = Network.Nodes_SortedByIndex.get(i);
                for (int j = 0; j < NodesCount; j++) {
                    Node NodeJ = Network.Nodes_SortedByIndex.get(j);
                    String Name = String.format("y(t%d)(i%d)(j%d)", t, i, j);

                    double LowerBound = 0;
                    double UpperBound = 1;

                    try {
                        if (!NodeI.AdjcantNodes.contains(NodeJ.ID)) {
                            y[t][i][j] = CPLEX.numVar(LowerBound, LowerBound, IloNumVarType.Int, Name);
                            //System.err.println(String.format("y[%d][%d][%d] = 0",t,i,j));
                        } else {
                            y[t][i][j] = CPLEX.numVar(LowerBound, UpperBound, IloNumVarType.Int, Name);
                        }

                    } catch (IloException ex) {
                        Logger.getLogger(RoutingProblem_CPLEX.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void Create_d_Variable() {
        int TaxisCount = Network.Taxis.size();
        int PassengersCount = Network.Passengers.size();
        Long[] PassengerIDs = Network.Passengers.keySet().toArray(new Long[PassengersCount]);
        Long[] TaxiIDs = Network.Taxis.keySet().toArray(new Long[PassengersCount]);
        d = new IloNumVar[PassengersCount][TaxisCount];
        D_Value = new int[PassengersCount][TaxisCount];
        for (int p = 0; p < PassengersCount; p++) {
            for (int t = 0; t < TaxisCount; t++) {
                String Name = String.format("d(P%d)(t%d)", p, t);

                double LowerBound = 0;
                double UpperBound = 1;

                try {


                    if (Network.OnBoardPassengers_SortedByPassengerID.containsKey(PassengerIDs[p])) {
                        OnBoardPassenger OnBoardPassenger = Network.OnBoardPassengers_SortedByPassengerID.get(PassengerIDs[p]);
                        if (OnBoardPassenger.TaxiID == TaxiIDs[t]) {
                            d[p][t] = CPLEX.numVar(UpperBound, UpperBound, IloNumVarType.Int, Name);
                        } else {
                            d[p][t] = CPLEX.numVar(LowerBound, LowerBound, IloNumVarType.Int, Name);
                        }
                    } else {
                        d[p][t] = CPLEX.numVar(LowerBound, UpperBound, IloNumVarType.Int, Name);
                    }

                } catch (IloException ex) {
                    Logger.getLogger(RoutingProblem_CPLEX.class
                            .getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    private void Create_u_Variable() {
        int TaxisCount = Network.Taxis.size();
        int NodesCount = Network.Nodes.size();

        u = new IloNumVar[NodesCount][TaxisCount];
        U_Value = new int[NodesCount][TaxisCount];
        for (int i = 0; i < NodesCount; i++) {
            for (int t = 0; t < TaxisCount; t++) {
                String Name = String.format("u(i%d)(t%d)", i, t);

                double LowerBound = 0;
                double UpperBound = LARGE_NUMBER;

                try {
                    u[i][t] = CPLEX.numVar(LowerBound, UpperBound, IloNumVarType.Float, Name);

                } catch (IloException ex) {
                    Logger.getLogger(RoutingProblem_CPLEX.class
                            .getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    private void SetVariable() {

        Create_x_Variable();
        Create_y_Variable();
        Create_d_Variable();
        Create_u_Variable();

    }

    //</editor-fold>
    private int t(int P) {
        if (Network.OnBoardPassengers_SortedByPassengerIndex.containsKey(P)) {
            return Network.Taxis.get(Network.OnBoardPassengers_SortedByPassengerIndex.get(P).TaxiID).Index;
        }
        return -1;
    }

    private int v(int T) {
        if (Network.Taxis_SortedByIndex.containsKey(T)) {
            return Network.Nodes.get(Network.Taxis_SortedByIndex.get(T).Location).Index;
        }
        return -1;
    }

    private int f(int P) {
        if (Network.Passengers_SortedByIndex.containsKey(P)) {
            return Network.Nodes.get(Network.Passengers_SortedByIndex.get(P).Destination).Index;
        }
        return -1;
    }

    private int s(int P) {
        if (Network.Passengers_SortedByIndex.containsKey(P)) {
            return Network.Nodes.get(Network.Passengers_SortedByIndex.get(P).Origin).Index;
        }
        return -1;
    }


    private int IndexOf(Node Node) {
        return Node.Index;
    }

    private int IndexOf(Passenger Passenger) {
        return Passenger.Index;
    }

    private int IndexOf(OnBoardPassenger OnBoardPassenger) {
        return OnBoardPassenger.Index;
    }

    private int IndexOf(Taxi Taxi) {
        return Taxi.Index;
    }

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~~  Creating Constraints ~~~~~~~~~~~~~~~~~~~~~~~~~~ ">
    private void SetConstraintSet_02() {

        int OnBoardPassengersCount = Network.OnBoardPassengers_SortedByPassengerID.size();
        try {

            Long[] OnBoardsPassengerIDs = Network.OnBoardPassengers_SortedByPassengerID.keySet().toArray(new Long[OnBoardPassengersCount]);

            for (int i = 0; i < OnBoardPassengersCount; i++) {
                IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();

                int PassengerIndex = IndexOf(Network.OnBoardPassengers_SortedByPassengerID.get(OnBoardsPassengerIDs[i]));
                int TaxiIndex = t(PassengerIndex);

                for (int j : Network.Nodes_SortedByIndex.keySet()) {

                    double Multiplier = 1;
                    try {
                        IloNumVar Term = x[PassengerIndex][TaxiIndex][v(TaxiIndex)][j];
                        ConstraintExpr.addTerm(Multiplier, Term);
                    }catch (Exception E){
                        int x=0;
                    }

                }
                CPLEX.addEq(ConstraintExpr, 1, String.format("ConstraintSet_2(P%d)", PassengerIndex));
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void SetConstraintSet_03() {

        int OnBoardPassengersCount = Network.OnBoardPassengers_SortedByPassengerID.size();
        try {

            Long[] OnBoardsPassengerIDs = Network.OnBoardPassengers_SortedByPassengerID.keySet().toArray(new Long[OnBoardPassengersCount]);

            for (int i = 0; i < OnBoardPassengersCount; i++) {
                IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();

                int PassengerIndex = IndexOf(Network.OnBoardPassengers_SortedByPassengerID.get(OnBoardsPassengerIDs[i]));
                int TaxiIndex = t(PassengerIndex);

                for (int j : Network.Nodes_SortedByIndex.keySet()) {

                    double Multiplier = 1;
                    IloNumVar Term = x[PassengerIndex][TaxiIndex][j][f(PassengerIndex)];
                    ConstraintExpr.addTerm(Multiplier, Term);
                }
                CPLEX.addEq(ConstraintExpr, 1, String.format("ConstraintSet_3(P%d)", PassengerIndex));
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void SetConstraintSet_04() {
        int PassengersCount = Network.SeekerPassengers.size();
        try {

            double Multiplier = 1;
            Long[] PassengerIDs = Network.SeekerPassengers.keySet().toArray(new Long[PassengersCount]);
            for (int PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                if (!Network.SeekerPassengers_SortedByIndex.containsKey(PassengerIndex)) {
                    continue;
                }

                for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {

                    IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                    for (int j =0; j < Network.Nodes.size(); j++) {
                        IloNumVar Term1 = x[PassengerIndex][TaxiIndex][j][s(PassengerIndex)];
                        ConstraintExpr.addTerm(Multiplier, Term1);
                    }
                    for (int j =0; j < Network.Nodes.size(); j++) {
                        IloNumVar Term2 = x[PassengerIndex][TaxiIndex][s(PassengerIndex)][j];
                        ConstraintExpr.addTerm(-Multiplier, Term2);
                    }
                    IloNumVar Term3 = d[PassengerIndex][TaxiIndex];
                    ConstraintExpr.addTerm(Multiplier, Term3);

                    CPLEX.addEq(ConstraintExpr, 0, String.format("ConstraintSet_4(P%d)[t%d)", PassengerIndex, TaxiIndex));
                }
            }
        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_05() {
        int PassengersCount = Network.SeekerPassengers.size();
        try {

            double Multiplier = 1;
            Long[] PassengerIDs = Network.SeekerPassengers.keySet().toArray(new Long[PassengersCount]);
            for (int PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                if (!Network.SeekerPassengers_SortedByIndex.containsKey(PassengerIndex)) {
                    continue;
                }

                for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {

                    IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                    for (int j =0; j < Network.Nodes.size(); j++) {
                        IloNumVar Term1 = x[PassengerIndex][TaxiIndex][j][f(PassengerIndex)];
                        ConstraintExpr.addTerm(Multiplier, Term1);
                    }
                    for (int j =0; j < Network.Nodes.size(); j++) {
                        IloNumVar Term2 = x[PassengerIndex][TaxiIndex][f(PassengerIndex)][j];
                        ConstraintExpr.addTerm(-Multiplier, Term2);
                    }
                    IloNumVar Term3 = d[PassengerIndex][TaxiIndex];
                    ConstraintExpr.addTerm(-Multiplier, Term3);

                    CPLEX.addEq(ConstraintExpr, 0, String.format("ConstraintSet_5(P%d)[t%d)", PassengerIndex, TaxiIndex));
                }
            }
        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

     private void SetConstraintSet_06() {
        try {

            Long[] NodeIDs = Network.Nodes.keySet().toArray(new Long[Network.Nodes.size()]);
            double Multiplier = 1;
            Long[] PassengerIDs = Network.Passengers.keySet().toArray(new Long[Network.Passengers.size()]);
            Long[] TaxiIDs = Network.Taxis.keySet().toArray(new Long[Network.Taxis.size()]);
            for (int PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                Passenger passenger = Network.Passengers.get(PassengerIDs[PassengerIndex]);
                for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                    Taxi taxi = Network.Taxis.get(TaxiIDs[TaxiIndex]);
                    for (int i = 0; i < Network.Nodes.size(); i++) {
                        if ((i == passenger.Destination)
                                || (i == passenger.Origin)
                                || (i== taxi.Location)) {
                            continue;
                        }
                        IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                        Node Node_I = Network.Nodes.get(NodeIDs[i]);
                        for (int jj = 0; jj < Node_I.AdjcantNodes.size(); jj++) {
                            int j = Network.Nodes.get(Node_I.AdjcantNodes.get(jj)).Index;

                            IloNumVar Term1 = x[PassengerIndex][TaxiIndex][j][i];
                            ConstraintExpr.addTerm(Multiplier, Term1);
                        }
                        for (Link Link : Node_I.OutLinks.values()) {
                            int j = Network.Nodes.get(Link.DownStream).Index;
                            IloNumVar Term2 = x[PassengerIndex][TaxiIndex][i][j];
                            ConstraintExpr.addTerm(-Multiplier, Term2);
                        }
                        CPLEX.addEq(ConstraintExpr, 0, String.format("ConstraintSet_6[p%d)[t%d)(i%d)", PassengerIndex, TaxiIndex, i));
                    }
                }
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_07() {
        try {

            Long[] NodeIDs = Network.Nodes.keySet().toArray(new Long[Network.Nodes.size()]);
            double Multiplier = 1;

            for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                for (int i = 0; i < Network.Nodes.size(); i++) {
                    for (int j = 0; j < Network.Nodes.size(); j++) {
                        IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                        for (int PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                            IloNumVar Term1 = x[PassengerIndex][TaxiIndex][i][j];
                            ConstraintExpr.addTerm(Multiplier, Term1);
                            IloNumVar Term2 = y[TaxiIndex][i][j];
                            ConstraintExpr.addTerm(-Multiplier * W[TaxiIndex], Term2);
                        }
                        CPLEX.addLe(ConstraintExpr, 0, String.format("ConstraintSet_7[t%d)(i%d)(j%d)", TaxiIndex, i, j));
                    }
                }
            }
        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_08() {
        try {

            double Multiplier = 1;
            Long[] PassengerIDs = Network.SeekerPassengers.keySet().toArray(new Long[Network.SeekerPassengers.size()]);
            for (int PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                if (!Network.SeekerPassengers_SortedByIndex.containsKey(PassengerIndex)) {
                    continue;
                }
                IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                    IloNumVar Term = d[PassengerIndex][TaxiIndex];
                    ConstraintExpr.addTerm(Multiplier, Term);
                }
                CPLEX.addLe(ConstraintExpr, 1, String.format("ConstraintSet_8(P%d)", PassengerIndex));
            }
        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_09() {
        try {
              Long[] NodeIDs = Network.Nodes.keySet().toArray(new Long[Network.Nodes.size()]);
            double Multiplier = 1;

            for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                for (int i = 0; i < Network.Nodes.size(); i++) {
                    IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                    for (int j = 0; j < Network.Nodes.size(); j++) {
                        IloNumVar Term  = y[TaxiIndex][j][i];

                        ConstraintExpr.addTerm(Multiplier, Term);
                    }
                    CPLEX.addLe(ConstraintExpr, 1, String.format("ConstraintSet_9(t%d)(i%d)", TaxiIndex, i));
                }

            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_10() {
        try {

            Long[] NodeIDs = Network.Nodes.keySet().toArray(new Long[Network.Nodes.size()]);

            double Multiplier = 1;
            for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                for (int i = 0; i < Network.Nodes.size(); i++) {
                    IloLinearNumExpr constraintExpr = CPLEX.linearNumExpr();
                    Node Node_I = Network.Nodes.get(NodeIDs[i]);
                    for (int j = 0; j < Network.Nodes.size(); j++) {
                        IloNumVar Term = y[TaxiIndex][i][j];

                        constraintExpr.addTerm(Multiplier, Term);
                    }

                    CPLEX.addLe(constraintExpr, 1, String.format("ConstraintSet_10(t%d)(i%d)", TaxiIndex, i));
                }
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_11() {
        try {

            double Multiplier = 1;
            Long[] NodeIDs = Network.Nodes.keySet().toArray(new Long[Network.Nodes.size()]);
            for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                for (int i = 0; i < Network.Nodes.size(); i++) {
                   if ((Network.V3.containsKey(NodeIDs[i])) || ((Network.V4.containsKey(NodeIDs[i])))) {
                        IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();

                        for (int j = 0; j < Network.Nodes.size(); j++) {
                            IloNumVar term1 = y[TaxiIndex][i][j];
                            ConstraintExpr.addTerm(Multiplier, term1);
                            IloNumVar term2 = y[TaxiIndex][j][i];
                            ConstraintExpr.addTerm(-Multiplier, term2);
                        }

                        CPLEX.addLe(ConstraintExpr, 0, String.format("ConstraintSet_11(t%d)(i%d)", TaxiIndex, i));
                    }
                }
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_12() {

        try {
            Long[] NodeIDs = Network.Nodes.keySet().toArray(new Long[Network.Nodes.size()]);
            double Multiplier = 1;
            for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                for (int i = 0; i < Network.Nodes.size(); i++) {
                 //   Node Node_I = Network.Nodes.get(NodeIDs[i]);
                 //   for (int jj = 0; jj < Node_I.AdjcantNodes.size(); jj++) {
                   //     int j = Network.Nodes.get(Node_I.AdjcantNodes.get(jj)).Index;
                        for (int j = 0; j < Network.Nodes.size(); j++) {
                        IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                        IloNumVar term1 = u[j][TaxiIndex];
                        ConstraintExpr.addTerm(Multiplier, term1);
                        IloNumVar term2 = u[i][TaxiIndex];
                        ConstraintExpr.addTerm(-Multiplier, term2);
                        IloNumVar term3 = y[TaxiIndex][i][j];
                        ConstraintExpr.addTerm(-Multiplier * LARGE_NUMBER, term3);
                        double maxRideTime = U[i][j] - LARGE_NUMBER;
                        CPLEX.addGe(ConstraintExpr, maxRideTime, String.format("ConstraintSet_12(t%d)(i%d)(j%d)", TaxiIndex, i, j));

                    }
                }
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetConstraintSet_13() {
        try {

            Long[] PassengerIDs = Network.SeekerPassengers.keySet().toArray(new Long[Network.SeekerPassengers.size()]);
            for (int PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                if (!Network.SeekerPassengers_SortedByIndex.containsKey(PassengerIndex)) {
                    continue;
                }
                for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                    IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                    double Multiplier = 1;
                    IloNumVar Term1 = u[s(PassengerIndex)][TaxiIndex];
                    ConstraintExpr.addTerm(Multiplier, Term1);
                    IloNumVar term2 = d[PassengerIndex][TaxiIndex];
                    ConstraintExpr.addTerm(-Multiplier * UE[PassengerIndex], term2);
                    CPLEX.addGe(ConstraintExpr, 0, String.format("ConstraintSet_13(%d)(%d)", PassengerIndex, TaxiIndex));

                }
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void SetConstraintSet_14() {
        try {

            Long[] PassengerIDs = Network.SeekerPassengers.keySet().toArray(new Long[Network.SeekerPassengers.size()]);
            for (int PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                    IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();
                    double Multiplier = 1;
                    IloNumVar Term1 = u[f(PassengerIndex)][TaxiIndex];
                    ConstraintExpr.addTerm(Multiplier, Term1);
                    IloNumVar term2 = d[PassengerIndex][TaxiIndex];
                    ConstraintExpr.addTerm(-Multiplier * UL[PassengerIndex], term2);
                    CPLEX.addLe(ConstraintExpr, 0, String.format("ConstraintSet_14(%d)(%d)", PassengerIndex, TaxiIndex));

                }
            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void SetConstraintSet_15() {
        try {

            int PassengerIndex;
            Long[] PassengerIDs = Network.Passengers.keySet().toArray(new Long[Network.Passengers.size()]);
            for (PassengerIndex = 0; PassengerIndex < Network.Passengers.size(); PassengerIndex++) {
                for (int TaxiIndex = 0; TaxiIndex < Network.Taxis.size(); TaxiIndex++) {
                    IloLinearNumExpr ConstraintExpr = CPLEX.linearNumExpr();

                    double Multiplier = 1;
                    IloNumVar Term1 = u[f(PassengerIndex)][TaxiIndex];
                    ConstraintExpr.addTerm(Multiplier, Term1);
                    IloNumVar Term2 = u[s(PassengerIndex)][TaxiIndex];
                    ConstraintExpr.addTerm(-Multiplier, Term2);
                    IloNumVar term3 = d[PassengerIndex][TaxiIndex];
                    ConstraintExpr.addTerm(LARGE_NUMBER, term3);
                    double Number = UP[PassengerIndex] + LARGE_NUMBER;
                    CPLEX.addLe(ConstraintExpr, Number, String.format("ConstraintSet_15(%d)(%d)", PassengerIndex, TaxiIndex));

                }

            }

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void SetConstraints() {

        SetConstraintSet_02();
        SetConstraintSet_03();

        SetConstraintSet_04();
        SetConstraintSet_05();
        SetConstraintSet_06();
        SetConstraintSet_07();
       SetConstraintSet_08();
        SetConstraintSet_09();
        SetConstraintSet_10();
        SetConstraintSet_11();
        SetConstraintSet_12();
        SetConstraintSet_13();
         SetConstraintSet_14();
       SetConstraintSet_15();


    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~~  Creating Objective Function ~~~~~~~~~~~~~~~~~~~~~~~~~~ ">
    private void SetObjectiveFuntion() {
        int NodesCount = Network.Nodes.size();
        int PassengersCount = Network.Passengers.size();
        int TaxisCount = Network.Taxis.size();

        try {
            IloLinearNumExpr ObjectiveFunctionExpr = CPLEX.linearNumExpr();

            for (int p = 0; p < PassengersCount; p++) {
                for (int t = 0; t < TaxisCount; t++) {
                    double Multiplier = R[p];
                    IloNumVar Term = d[p][t];
                    ObjectiveFunctionExpr.addTerm(Multiplier, Term);
                }

            }

            for (int i = 0; i < NodesCount; i++) {
                for (int j = 0; j < NodesCount; j++) {
                    for (int t = 0; t < TaxisCount; t++) {

                        double Multiplier = -C[i][j];
                        IloNumVar Term = y[t][i][j];
                        ObjectiveFunctionExpr.addTerm(Multiplier, Term);
                    }
                }
            }
            CPLEX.addMaximize(ObjectiveFunctionExpr);

        } catch (IloException ex) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~~  Solvinbg CPLEX Model Function ~~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    private boolean SolveProblem() {

        try {
            String ProblemModelFileName = String.format("%s\\%s", Network.OutputDirectory, "Model2.lp");
            CPLEX.exportModel(ProblemModelFileName);

            if (CPLEX.solve()) {
                double ObjectiveValue = CPLEX.getObjValue();
                System.out.println(String.format("Objective Value = %f", ObjectiveValue));

                int NodesCount = Network.Nodes.size();
                int PassengersCount = Network.Passengers.size();
                int TaxisCount = Network.Taxis.size();


                return true;
                // To Extract The Data from the Variables
                // String Variable = String.format("y(%d)(%d)(%d)", t, i, j);
                // int Value = CPLEX.getValue(Variable);
            } else {
                System.out.print("Infeasable");
                return false;
            }
        } catch (Exception Exception) {
            Logger.getLogger(RoutingProblem_CPLEX.class
                    .getName()).log(Level.SEVERE, null, Exception);
            return false;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~~  Solvinbg CPLEX Model Function ~~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    private void Get_x_Variable() {
        int NodesCount = Network.Nodes.size();
        int PassengersCount = Network.Passengers.size();
        int TaxisCount = Network.Taxis.size();
        System.out.println("X_Values");
        for (int p = 0; p < PassengersCount; p++) {
            for (int t = 0; t < TaxisCount; t++) {
                for (int i = 0; i < NodesCount; i++) {
                    for (int j = 0; j < NodesCount; j++) {
                        try {
                            X_Value[p][t][i][j] = (int) CPLEX.getValue(x[p][t][i][j]);
                            if (X_Value[p][t][i][j] > 0) {
                                System.out.println(String.format("X_Value[%d][%d][%d][%d] =\t%d", p, t, i, j, X_Value[p][t][i][j]));
                            }
                        } catch (Exception ex) {

                        }
                    }
                }
            }
        }
    }

    private void Get_y_Variable() {
        int NodesCount = Network.Nodes.size();
        int TaxisCount = Network.Taxis.size();
        System.out.println("Y_Values");
        for (int t = 0; t < TaxisCount; t++) {
            for (int i = 0; i < NodesCount; i++) {
                for (int j = 0; j < NodesCount; j++) {
                    try {
                        Y_Value[t][i][j] = (int) CPLEX.getValue(y[t][i][j]);
                        System.out.println(String.format("Y_Value[%d][%d][%d] =\t%d", t, i, j, Y_Value[t][i][j]));
                    } catch (Exception ex) {

                    }
                }
            }
        }
    }

    private void Get_d_Variable() {
        int TaxisCount = Network.Taxis.size();
        int PassengersCount = Network.Passengers.size();
        System.out.println("d_Values");
        for (int p = 0; p < PassengersCount; p++) {
            for (int t = 0; t < TaxisCount; t++) {

                try {
                    D_Value[p][t] = (int) CPLEX.getValue(d[p][t]);
                    System.out.println(String.format("d_Value[%d][%d] =\t%d", p, t, D_Value[p][t]));
                } catch (Exception ex) {

                }
            }
        }
    }

    private void Get_u_Variable() {
        int TaxisCount = Network.Taxis.size();
        int NodesCount = Network.Nodes.size();
        System.out.println("u_Values");

        for (int i = 0; i < NodesCount; i++) {
            for (int t = 0; t < TaxisCount; t++) {
                try {
                    U_Value[i][t] = (int) CPLEX.getValue(u[i][t]);
                    System.out.println(String.format("u_Value[%d][%d] =\t%d", i, t, U_Value[i][t]));
                } catch (Exception ex) {

                }
            }
        }
    }

    private void ExtractResults() {
        Get_x_Variable();
        //Get_y_Variable();
        //  Get_d_Variable();
        // Get_u_Variable();
    }
    //</editor-fold>
}
