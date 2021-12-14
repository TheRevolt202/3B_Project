package mp3gui;
//импорт использованных библиотек
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import objects.MP3Player;
import objects.MP3;
import utils.FileUtils;
import utils.MP3PlayerFileFilter;
import utils.SkinUtils;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;





public class mp3 extends javax.swing.JFrame implements BasicPlayerListener  {

  //<editor-fold defaultstate="collapsed" desc="Constants">
    private static final String MP3_FILE_EXTENSION = "mp3";
    private static final String MP3_FILE_DESCRIPTION = "Файлы mp3";
    private static final String PLAYLIST_FILE_EXTENSION = "pls";
    private static final String PLAYLIST_FILE_DESCRIPTION = "Файлы плейлиста";
    private static final String EMPTY_STRING = "";
    private static final String INPUT_SONG_NAME = "введите имя песни";
    //</editor-fold>
  private DefaultListModel mp3ListModel = new DefaultListModel();
    private FileFilter mp3FileFilter = new MP3PlayerFileFilter(MP3_FILE_EXTENSION, MP3_FILE_DESCRIPTION);
    private FileFilter playlistFileFilter = new MP3PlayerFileFilter(PLAYLIST_FILE_EXTENSION, PLAYLIST_FILE_DESCRIPTION);
    private MP3Player player = new MP3Player(this);
   
   
 //<editor-fold defaultstate="collapsed" desc="Variables to scroll through songs">
    private long secondsAmount; 
    private long duration; 
    private int bytesLen; 
    private double posValue = 0.0;
    private boolean movingFromJump = false;
    private boolean moveAutomatic = false;
      private int currentVolumeValue;
    //</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="player event listener "BasicPlayerListener"">
    @Override
    public void opened(Object o, Map map) { //открытие песни

        duration = (long) Math.round((((Long) map.get("duration")).longValue()) / 1000000); //поиск в карте длины песни
        bytesLen = (int) Math.round(((Integer) map.get("mp3.length.bytes")).intValue()); //поиск в карте обьема

        String songName = map.get("title") != null ? map.get("title").toString() : FileUtils.getFileNameWithoutExtension(new File(o.toString()).getName());

        if (songName.length() > 30) {
            songName = songName.substring(0, 30) + "...";
        }

        labelSongName.setText(songName);

    }

    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) { //отоброжение прогресса песни

        float progress = -1.0f;

        if ((bytesread > 0) && ((duration > 0))) {
            progress = bytesread * 1.0f / bytesLen * 1.0f;
        }


        secondsAmount = (long) (duration * progress);

        if (duration != 0) {
            if (movingFromJump == false) {
                slideProgress.setValue(((int) Math.round(secondsAmount * 1000 / duration)));

            }
        }
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) { //обновление проигрывания при ручном двигание ползунка длины песни
        int state = bpe.getCode();

        if (state == BasicPlayerEvent.PLAYING) {
            movingFromJump = false;
        } else if (state == BasicPlayerEvent.SEEKING) {
            movingFromJump = true;
        } else if (state == BasicPlayerEvent.EOM) {
            if (selectNextSong()) {
                playFile();
            }
        }


    }

    @Override
    public void setController(BasicController bc) {
    }
    //</editor-fold>
           
           public mp3() {
        initComponents();
    }

        private void playFile() { //проигрывание файла
        int[] indexPlayList = lstPlayList.getSelectedIndices();
        if (indexPlayList.length > 0) {
            MP3 mp3 = (MP3) mp3ListModel.getElementAt(indexPlayList[0]);
            player.play(mp3.getPath());
            player.setVolume(slideVolume.getValue(), slideVolume.getMaximum());
        }

    }
               
           private boolean selectPrevSong() { //выбор предыдущей песни
        int nextIndex = lstPlayList.getSelectedIndex() - 1;
        if (nextIndex >= 0) {
            lstPlayList.setSelectedIndex(nextIndex);
            return true;
        }

        return false;
    }
           
           private boolean selectNextSong() {//выбор следующей песни
        int nextIndex = lstPlayList.getSelectedIndex() + 1;
        if (nextIndex <= lstPlayList.getModel().getSize() - 1) {
            lstPlayList.setSelectedIndex(nextIndex);
            return true;
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        findsong = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSearch = new javax.swing.JTextPane();
        slideVolume = new javax.swing.JSlider();
        mute = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        addSong = new javax.swing.JButton();
        deleteSong = new javax.swing.JButton();
        downSong = new javax.swing.JButton();
        upSong = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        prevSong = new javax.swing.JButton();
        play = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        nextSong = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstPlayList = new javax.swing.JList<>();
        labelSongName = new javax.swing.JLabel();
        slideProgress = new javax.swing.JSlider();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        save = new javax.swing.JMenuItem();
        openpls = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        Skin1 = new javax.swing.JMenuItem();
        Skin2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        findsong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-osu-20.png"))); // NOI18N
        findsong.setText("Найти");
        findsong.setToolTipText("Найти песню");
        findsong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findsongActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(txtSearch);

        slideVolume.setMaximum(200);
        slideVolume.setMinorTickSpacing(5);
        slideVolume.setSnapToTicks(true);
        slideVolume.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slideVolumeStateChanged(evt);
            }
        });

        mute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-громкий-звук-20.png"))); // NOI18N
        mute.setToolTipText("Вкл/выкл звук");
        mute.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-выключить-звук-20.png"))); // NOI18N
        mute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(slideVolume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(findsong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(findsong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(slideVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mute))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-добавить-песню-20.png"))); // NOI18N
        addSong.setToolTipText("добавить песню");
        addSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongActionPerformed(evt);
            }
        });

        deleteSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-удалить-навсегда-20.png"))); // NOI18N
        deleteSong.setToolTipText("удалить песню");
        deleteSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSongActionPerformed(evt);
            }
        });

        downSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/вверх.png"))); // NOI18N
        downSong.setToolTipText("выбрать прелыдущую песню");
        downSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downSongActionPerformed(evt);
            }
        });

        upSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/вниз.png"))); // NOI18N
        upSong.setToolTipText("выбрать следующую песню");
        upSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upSongActionPerformed(evt);
            }
        });

        prevSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-налево-20.png"))); // NOI18N
        prevSong.setToolTipText("предыдущая песня");
        prevSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevSongActionPerformed(evt);
            }
        });

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-воспроизведение-20.png"))); // NOI18N
        play.setToolTipText("воспроизвести");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-стоп-20.png"))); // NOI18N
        stop.setToolTipText("остановить");
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

        nextSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-направо-20.png"))); // NOI18N
        nextSong.setToolTipText("следующая песня");
        nextSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextSongActionPerformed(evt);
            }
        });

        pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-пауза-20.png"))); // NOI18N
        pause.setToolTipText("пауза");
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        lstPlayList.setModel(mp3ListModel);
        lstPlayList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstPlayListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstPlayList);

        slideProgress.setMaximum(1000);
        slideProgress.setSnapToTicks(true);
        slideProgress.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slideProgressStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(slideProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(prevSong, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(addSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(play, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(deleteSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(pause)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stop, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(upSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(downSong, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(nextSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelSongName)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteSong, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(downSong)
                    .addComponent(upSong)
                    .addComponent(addSong))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(play)
                    .addComponent(pause)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(prevSong)
                        .addComponent(stop)
                        .addComponent(nextSong)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelSongName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(slideProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("File");

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-сохранить-все-20.png"))); // NOI18N
        save.setText("Сохранить плейлист");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jMenu1.add(save);

        openpls.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-аудиофайл-20.png"))); // NOI18N
        openpls.setText("Добавить плейлист");
        openpls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openplsActionPerformed(evt);
            }
        });
        jMenu1.add(openpls);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenu3.setText("Выбор скина");

        Skin1.setText("пресет 1");
        Skin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Skin1ActionPerformed(evt);
            }
        });
        jMenu3.add(Skin1);

        Skin2.setText("пресет 2");
        Skin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Skin2ActionPerformed(evt);
            }
        });
        jMenu3.add(Skin2);

        jMenuItem1.setText("пресет 3");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSongActionPerformed
        int [] indexPlayList = lstPlayList.getSelectedIndices(); //удаление песни
        if (indexPlayList.length > 0) {
            ArrayList<MP3> mp3ListForRemove = new ArrayList<MP3>();
            for (int i=0; i < indexPlayList.length; i++) {
                MP3 mp3= (MP3) mp3ListModel.getElementAt(indexPlayList[i]);
                mp3ListForRemove.add(mp3);
            }

            for (MP3 mp3 : mp3ListForRemove){
                mp3ListModel.removeElement(mp3);
            }
            
        }
    }//GEN-LAST:event_deleteSongActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        player.pause(); //постановка на паузу
    }//GEN-LAST:event_pauseActionPerformed

    private void Skin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Skin1ActionPerformed
   SkinUtils.changeSkin(this, UIManager.getSystemLookAndFeelClassName());
   fileChooser.updateUI(); //смена скина 1
    }//GEN-LAST:event_Skin1ActionPerformed

    private void Skin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Skin2ActionPerformed
        SkinUtils.changeSkin(this, new NimbusLookAndFeel()); //смена скина 2
    }//GEN-LAST:event_Skin2ActionPerformed

    private void addSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongActionPerformed
        FileUtils.addFileFilter(fileChooser, mp3FileFilter); //добавление песни в плейлист
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION){
            
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles){
                MP3 mp3 = new MP3(file.getName(), file.getPath());
                mp3ListModel.addElement(mp3);
            }
            
        }
    }//GEN-LAST:event_addSongActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        player.stop(); //остановка воспроизведения
    }//GEN-LAST:event_stopActionPerformed

    private void upSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upSongActionPerformed
        int nextIndex = lstPlayList.getSelectedIndex() + 1; //выделение песни в плейлисте сверху
        if (nextIndex <= lstPlayList.getModel().getSize() - 1){
            lstPlayList.setSelectedIndex(nextIndex);
        }
    }//GEN-LAST:event_upSongActionPerformed

    private void downSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downSongActionPerformed
        int nextIndex = lstPlayList.getSelectedIndex() - 1; //выбор песни в плейлисте снизу
        if (nextIndex >=0){
            lstPlayList.setSelectedIndex(nextIndex);
        }
    }//GEN-LAST:event_downSongActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        int [] indexPlayList = lstPlayList.getSelectedIndices(); //проверка на проигрывание файла
        if (indexPlayList.length > 0){
            MP3 mp3 =(MP3) mp3ListModel.getElementAt(indexPlayList[0]);
            player.play(mp3.getPath());
            player.setVolume(slideVolume.getValue(),slideVolume.getMaximum()); //установка звука в зависимости от состояния песни
        }
    }//GEN-LAST:event_playActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        FileUtils.addFileFilter(fileChooser, playlistFileFilter); //сохранение плейлистов
        int result =  fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists()) {
                
                int resultOveride = JOptionPane.showConfirmDialog(this, "Файл существует", "Перезаписать", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (resultOveride) {
                    case JOptionPane.NO_OPTION:
                    saveActionPerformed(evt);
                    return;
                    case JOptionPane.CANCEL_OPTION:
                    fileChooser.cancelSelection();
                    return; 
                }
                fileChooser.approveSelection();
            }
            
            String fileExtension = FileUtils.getFileExtension(selectedFile);
            String fileNameForSave = (fileExtension != null && fileExtension.equals(PLAYLIST_FILE_EXTENSION)) ? selectedFile.getPath(): selectedFile.getPath()+"."+PLAYLIST_FILE_EXTENSION;
            
        FileUtils.serialize(mp3ListModel, fileNameForSave);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void openplsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openplsActionPerformed
        FileUtils.addFileFilter(fileChooser, playlistFileFilter); //открытие плейлиста
        int result = fileChooser.showOpenDialog(this);
        
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            DefaultListModel mp3ListModel= (DefaultListModel) FileUtils.deserialize(selectedFile.getPath());
            this.mp3ListModel=mp3ListModel;
            lstPlayList.setModel(mp3ListModel);
        }
    }//GEN-LAST:event_openplsActionPerformed

    private void findsongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findsongActionPerformed
        String searchStr = txtSearch.getText(); //поиск песни в плейлисте
        
        if (searchStr == null || searchStr.trim().equals(EMPTY_STRING)) {
            return;
        }
        
        ArrayList<Integer> mp3FindedIndexes = new ArrayList<Integer>();
        
        for (int i=0; i < mp3ListModel.size(); i++) {
            MP3 mp3 = (MP3) mp3ListModel.getElementAt(i);
            if (mp3.getName().toUpperCase().contains(searchStr.toUpperCase())){
                mp3FindedIndexes.add(i);
            }
        }
        
        int [] selectIndexes = new int[mp3FindedIndexes.size()];
        
        if (selectIndexes.length == 0) {
            JOptionPane.showMessageDialog(this, "Поиск по строке \'"+ searchStr + "\' не дал результатов");
            txtSearch.requestFocus();
            txtSearch.selectAll();
            return;
        }
        for (int i = 0; i< selectIndexes.length; i++){
            selectIndexes[i]= mp3FindedIndexes.get(i).intValue();
        }
        lstPlayList.setSelectedIndices(selectIndexes);
    }//GEN-LAST:event_findsongActionPerformed

    private void processSeek(double bytes) { //ручное переход по песне
        try {
            long skipBytes = (long) Math.round(((Integer) bytesLen).intValue() * bytes);
            player.jump(skipBytes);
        } catch (Exception e) {
            e.printStackTrace();
            movingFromJump = false;
        }

    }
    private void lstPlayListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstPlayListMouseClicked
       if (evt.getModifiers()==InputEvent.BUTTON1_MASK && evt.getClickCount() ==2) { //воспроизведение песни по нажатию левой кнопки мыши
           int [] indexPlayList = lstPlayList.getSelectedIndices();
           if (indexPlayList.length > 0) {
               MP3 mp3=(MP3)mp3ListModel.getElementAt(indexPlayList[0]);
               player.play(mp3.getPath());
               player.setVolume(slideVolume.getValue(),slideVolume.getMaximum());
           }
       }
    }//GEN-LAST:event_lstPlayListMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        SkinUtils.changeSkin(this, new NoireLookAndFeel()); //смена скина 3
        fileChooser.updateUI();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void prevSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevSongActionPerformed
         if (selectPrevSong()) { //переключение на следующую песню
            playFile();
        }
    }//GEN-LAST:event_prevSongActionPerformed

    private void nextSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextSongActionPerformed
        if (selectNextSong()) { //переключение на предыдущую песню
            playFile();
        }
    }//GEN-LAST:event_nextSongActionPerformed

    private void muteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteActionPerformed
       
        if (mute.isSelected()) { //проверка состояния кнопки громкости
            currentVolumeValue = slideVolume.getValue();
            slideVolume.setValue(0);
        } else {
            slideVolume.setValue(currentVolumeValue);
        }
    }//GEN-LAST:event_muteActionPerformed

    private void slideProgressStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slideProgressStateChanged
      
        if (slideProgress.getValueIsAdjusting() == false) { //автоматическое продвижелия ползунка прогресса песни
            if (moveAutomatic == true) {
                moveAutomatic = false;
                posValue = slideProgress.getValue() * 1.0 / 1000;
                processSeek(posValue);
            }
        } else {
            moveAutomatic = true;
            movingFromJump = true;
        }

    }//GEN-LAST:event_slideProgressStateChanged

    private void slideVolumeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slideVolumeStateChanged
         player.setVolume(slideVolume.getValue(), slideVolume.getMaximum()); //автоматическое продвижелия ползунка прогресса громкости
        
        if (slideVolume.getValue()==0){
            mute.setSelected(true);
        } else{
            mute.setSelected(false);
        }
    }//GEN-LAST:event_slideVolumeStateChanged

    public void txtSearchFocusGained(java.awt.event.FocusEvent evt){ //установка фокуса при начале использования поиска
        if (txtSearch.getText().trim().endsWith(INPUT_SONG_NAME)){
            txtSearch.setText(EMPTY_STRING);
        }
    }
    
    public void txtSearchFocusLost(java.awt.event.FocusEvent evt){ //потеря фокуса при окончании использования поиска
        if (txtSearch.getText().trim().endsWith(EMPTY_STRING)){
            txtSearch.setText(INPUT_SONG_NAME);
        }
    }
    
    
    
    public static void main(String args[]) { //главный класс
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mp3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Skin1;
    private javax.swing.JMenuItem Skin2;
    private javax.swing.JButton addSong;
    private javax.swing.JButton deleteSong;
    private javax.swing.JButton downSong;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton findsong;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelSongName;
    private javax.swing.JList<String> lstPlayList;
    private javax.swing.JToggleButton mute;
    private javax.swing.JButton nextSong;
    private javax.swing.JMenuItem openpls;
    private javax.swing.JButton pause;
    private javax.swing.JButton play;
    private javax.swing.JButton prevSong;
    private javax.swing.JMenuItem save;
    private javax.swing.JSlider slideProgress;
    private javax.swing.JSlider slideVolume;
    private javax.swing.JButton stop;
    private javax.swing.JTextPane txtSearch;
    private javax.swing.JButton upSong;
    // End of variables declaration//GEN-END:variables

}