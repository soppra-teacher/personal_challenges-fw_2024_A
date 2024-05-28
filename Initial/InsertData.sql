-- 費目マスタ
INSERT INTO MST_HIMOKU VALUES ('01', '繰り越し分', '1', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_HIMOKU VALUES ('02', '給与', '1', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_HIMOKU VALUES ('03', '食費', '2', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_HIMOKU VALUES ('04', '光熱費', '2', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_HIMOKU VALUES ('05', '宝くじ当選', '1', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_HIMOKU VALUES ('06', '賞与', '1', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_HIMOKU VALUES ('07', 'プレゼント', '2', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_HIMOKU VALUES ('08', 'ギャンブル', '2', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
-- コード種別マスタ
INSERT INTO MST_CODE_CLASS VALUES ('001', '費目', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE_CLASS VALUES ('002', '性別', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE_CLASS VALUES ('003', '続柄', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE_CLASS VALUES ('004', '世帯主', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE_CLASS VALUES ('005', '削除フラグ', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
-- コードマスタ
INSERT INTO MST_CODE VALUES ('001', '1', '収入', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('001', '2', '支出', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('002', '1', '男', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('002', '2', '女', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '1', '父', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '2', '母', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '3', '長男', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '4', '長女', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '5', '次男', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '6', '次女', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '7', '三男', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('003', '8', '三女', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('004', '1', '世帯主', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('005', '1', '削除データを除く', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_CODE VALUES ('005', '2', '削除データのみ', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
-- 世帯マスタ
INSERT INTO MST_SETAI VALUES ('00000000', '管理者', 'ｶﾝﾘｼｬ', '000-0000', '秘密', '000-000-0000', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
INSERT INTO MST_SETAI VALUES ('00000001', '山田家', 'ﾔﾏﾀﾞｹ', '660-0893', '兵庫県尼崎市西難波町', '06-7474-8888', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1);
-- 個人マスタ
INSERT INTO MST_KOJIN VALUES ('1', '00000000', '1', '管理者', 'ｶﾝﾘｼｬ', '1', '1', '1', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1, NULL);
INSERT INTO MST_KOJIN VALUES ('00000001', '00000001', '1', '山田　太郎', 'ﾔﾏﾀﾞ ﾀﾛｳ', '1', '1', '1', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1, NULL);
INSERT INTO MST_KOJIN VALUES ('00000002', '00000001', '2', '山田　花子', 'ﾔﾏﾀﾞ ﾊﾅｺ', '2', '2', '0', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1, NULL);
INSERT INTO MST_KOJIN VALUES ('00000003', '00000001', '3', '山田　一郎', 'ﾔﾏﾀﾞ ｲﾁﾛｳ', '1', '3', '0', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1, NULL);
INSERT INTO MST_KOJIN VALUES ('00000004', '00000001', '4', '山田　次郎', 'ﾔﾏﾀﾞ ｼﾞﾛｳ', '1', '5', '0', '0', 'soppra', SYSDATE, 'soppra', SYSDATE, 1, NULL);
