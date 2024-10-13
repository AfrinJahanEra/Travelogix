package Checklist_NoteKeeping;

import Checklist_NoteKeeping.MainFuctionalities.CheckList.CheckListdashBoard;
import Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.VoiceNote.VoiceNoteDashBoard;
import Checklist_NoteKeeping.MainFuctionalities.NoteKeeping.WriteNote.NotewritingDashboard;

public class Main {
    public static void main(String[] args) {

        // CheckListdashBoard dashboard = new CheckListdashBoard();
        // dashboard.displayChecklist();

        // VoiceNoteDashBoard voiceNoteDashBoard = new VoiceNoteDashBoard();
        // voiceNoteDashBoard.voiceNoteDashBoard();

        NotewritingDashboard notewritingDashboard = new NotewritingDashboard();
        notewritingDashboard.notewritingDashboard();

    }
}
